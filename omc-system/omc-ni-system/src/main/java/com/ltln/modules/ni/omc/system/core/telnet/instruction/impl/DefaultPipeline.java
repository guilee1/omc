package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.ICmdParser;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.IPipeline;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class DefaultPipeline implements IPipeline {

	@Override
	public void execute(Operator user,ChannelHandlerContext ctx, String fullCmd) {
		if(StringUtils.isEmpty(fullCmd)){
			String errorInfo = String.format("empty command?");
			Logger.info(errorInfo);
			ctx.writeAndFlush(errorInfo+Constants.END_TAG);
		}else{
			String[] allStrCommands = fullCmd.split(Constants.INSTRUCTION_CMD_SPLITER);
			List<AbsNmsReq> instructionCmdList = new ArrayList<>();
			for(String strCommand : allStrCommands){
				if(StringUtils.isEmpty(StringUtils.trim(strCommand)))
					continue;
				String lowerCaseStrCommand = strCommand.trim();//.toLowerCase();
				ICmdParser cmdParser = getParser(lowerCaseStrCommand);
				if(cmdParser==null){
					String errorInfo = String.format("unknow command? not support [%s] yet!", strCommand);
					Logger.info(errorInfo);
					ctx.writeAndFlush(errorInfo+Constants.END_TAG);
					return;
				}else{
					AbsNmsReq instructionResult = cmdParser.decode(lowerCaseStrCommand);
					if(instructionResult.badFormat()){
						String errorInfo = String.format("command chk error reason is [%s]", instructionResult.getErrorMsg());
						Logger.info(errorInfo);
						ctx.writeAndFlush(errorInfo+Constants.END_TAG);
						return;
					}else{
						instructionCmdList.add(instructionResult);
					}
				}
			}
			for(AbsNmsReq cmdItem : instructionCmdList){
				InstructionHandler handler = getHandler(cmdItem.getType());
				handler.process(user,ctx, cmdItem);
			}
		}
	}

	@Override
	public ICmdParser getParser(String lowerCaseStrCommand) {
		return SelfBeanFactoryAware.getBean(EInsCmdType.parserByLowerCaseCmd(lowerCaseStrCommand));
	}


	@Override
	public InstructionHandler getHandler(EInsCmdType type) {
		return SelfBeanFactoryAware.getBean(type.handler);
	}


}
