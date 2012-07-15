package java.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import org.apache.harmony.niochar.charset.UTF_8;

public abstract class Charset implements Comparable<Charset>
{
	private static Charset _defaultCharset = new UTF_8("UTF-8", null);
	
	public static Charset defaultCharset()
	{
		return _defaultCharset;
	}
	
	public static boolean isSupported(String encoding)
	{
		return encoding.equals("UTF-8");
	}

	public static Charset forName(String encoding) throws UnsupportedCharsetException
	{
		if (encoding.equals(_defaultCharset.name()))
			return _defaultCharset;
		throw new UnsupportedCharsetException(encoding);
	}
	
	private static String _canonicalName;
	
	public Charset(String canonicalName, String[] aliases)
	{
		_canonicalName = canonicalName;
	}
	
	public String name()
	{
		return _canonicalName;
	}
	
    public int compareTo(Charset charset) {
        return this._canonicalName.compareToIgnoreCase(charset._canonicalName);
    }

	public abstract boolean contains(Charset cs);
    public abstract CharsetDecoder newDecoder();
    public abstract CharsetEncoder newEncoder();

    /**
     * Encodes the content of the give character buffer and outputs to a byte
     * buffer that is to be returned.
     * <p>
     * The default action in case of encoding errors is
     * <code>CodingErrorAction.REPLACE</code>.
     *
     * @param buffer
     *            the character buffer containing the content to be encoded.
     * @return the result of the encoding.
     */
    public final ByteBuffer encode(CharBuffer buffer) {
        try {
            return this.newEncoder()
                    .onMalformedInput(CodingErrorAction.REPLACE)
                    .onUnmappableCharacter(CodingErrorAction.REPLACE).encode(
                            buffer);

        } catch (CharacterCodingException ex) {
            throw new Error(ex.getMessage(), ex);
        }
    }

    /**
     * Encodes a string and outputs to a byte buffer that is to be returned.
     * <p>
     * The default action in case of encoding errors is
     * <code>CodingErrorAction.REPLACE</code>.
     *
     * @param s
     *            the string to be encoded.
     * @return the result of the encoding.
     */
    public final ByteBuffer encode(String s) {
        return encode(CharBuffer.wrap(s));
    }

    /**
     * Decodes the content of the specified byte buffer and writes it to a
     * character buffer that is to be returned.
     * <p>
     * The default action in case of decoding errors is
     * <code>CodingErrorAction.REPLACE</code>.
     * 
     * @param buffer
     *            the byte buffer containing the content to be decoded.
     * @return a character buffer containing the output of the decoding.
     */
    public final CharBuffer decode(ByteBuffer buffer) {

        try {
            return this.newDecoder()
                    .onMalformedInput(CodingErrorAction.REPLACE)
                    .onUnmappableCharacter(CodingErrorAction.REPLACE).decode(
                            buffer);

        } catch (CharacterCodingException ex) {
            throw new Error(ex.getMessage(), ex);
        }
    }
}
