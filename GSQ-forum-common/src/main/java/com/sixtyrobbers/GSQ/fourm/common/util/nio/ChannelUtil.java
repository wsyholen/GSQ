package com.sixtyrobbers.GSQ.fourm.common.util.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/** <p>Title: ChannelUtil</p>  
 * <p>Description: 使用通道调用操作系统命令进行拷贝</p>  
 * @author houzw@neusoft.com  
 * @date 2019年1月6日  
 */  
public abstract class ChannelUtil {

    private static final int BUFFER_SIZE = 16384;

    public static void copy(InputStream input, OutputStream output) throws IOException {
        ReadableByteChannel srcChannel = Channels.newChannel(input);

        WritableByteChannel destChannel = Channels.newChannel(output);

        copy(srcChannel, destChannel);

    }

    public static void copyAndClose(InputStream input, OutputStream output) throws IOException {
        ReadableByteChannel srcChannel = Channels.newChannel(input);

        WritableByteChannel destChannel = Channels.newChannel(output);
        try {
            copy(srcChannel, destChannel);
        }

        finally {
            destChannel.close();
            srcChannel.close();
        }

    }

    private static void copy(ReadableByteChannel srcChannel, WritableByteChannel destChannel) throws IOException {
        InputStream inputStream = new NIOBufferedInputStream(srcChannel, BUFFER_SIZE);

        OutputStream outputStream = new NIOOutputStream(destChannel, BUFFER_SIZE);

        StreamUtil.io(inputStream, outputStream);
    }

    public static void copy2NioOut(InputStream inputStream, OutputStream outputStream) throws IOException {

        WritableByteChannel destChannel = Channels.newChannel(outputStream);
        OutputStream output = new NIOOutputStream(destChannel, BUFFER_SIZE);

        StreamUtil.io(inputStream, output);
    }

    public static void copy2NioOutAndClose(InputStream inputStream, OutputStream outputStream) throws IOException {

        try {
            copy2NioOut(inputStream, outputStream);
        }

        finally {
            outputStream.close();
            inputStream.close();
        }
    }
}
