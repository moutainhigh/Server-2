package com.framework.client.socket.codec;


import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CodeFactoryForClient implements ProtocolCodecFactory {
    private final ProtocolEncoder encoder;

    private final ProtocolDecoder decoder;

    public CodeFactoryForClient() {
        encoder = new EncoderForClient();
        decoder = new DecoderForClient();
    }
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}
}
