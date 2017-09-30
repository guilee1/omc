package com.ltln.modules.ni.omc.system.core.alarm.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.EMsgType;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class AlarmMsgDecoder extends LengthFieldBasedFrameDecoder {

	private static final int HEADER_SIZE = 2+1+4+2;
	
    public AlarmMsgDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
            int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                lengthAdjustment, initialBytesToStrip, failFast);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf frame)throws Exception {
    	ByteBuf in = (ByteBuf) super.decode(ctx, frame);
    	if (in == null) 
            return null;
        if (in.readableBytes() < HEADER_SIZE) {
            return null;
        }
        
        byte[] sign = new byte[2];
        in.readBytes(sign);
        if(!Toolkit.chkNmsMsgStartSign(sign)){
        	Logger.info("invalid Sign??");
        	return null;
        }
        
        byte type = in.readByte();
        EMsgType msgType;
        try{
        	msgType = EMsgType.from(Toolkit.bytesToInt(type));
        }catch (RuntimeException e) {
        	Logger.info("invalid EMsgType??");
			return null;
		}
        
        int timeStamp = in.readInt();
        
        byte[] bLength = new byte[2];
        in.readBytes(bLength);
        int length = Toolkit.doubleBytesToInt(bLength);
       
        if (in.readableBytes() < length) {
        	Logger.info("not enough length??");
        	return null;
        }
        byte[] req = new byte[length];
        in.readBytes(req);
        String body = new String(req, Constants.ENCODING);
        NmsMsg msg = new NmsMsg(msgType,timeStamp,length,body);
        return msg;
    }
}
