package jnit.crypto;


/**
 * JNIT 암호화 서비스
 * spiring/context-crypto.xml에 설정
 * @author KMK
 *
 */
public interface JnitCryptoService {

    /**
     * 암호화(url인코딩포함)
     * @param str
     * @return
     */
	public String encrypt(String str);
	

    /**
     * 복호화(url인코딩포함)
     * @param str
     * @return
     */
	public String decrypt(String str);

    /**
     * 암호화
     * @param str
     * @return
     */
	public String encryptNoneEncoding(String str);
	
    /**
     * 복호화
     * @param str
     * @return
     */
	public String decryptNoneEncoding(String str);
	
    /**
     * 복호화(get방식 parameter +기호 변환)
     * @param str
     * @return
     */
	public String decryptNoneEncodingParameter(String str);
}
