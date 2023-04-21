package jnit.crypto;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;

/**
 * JNIT 암호화 서비스
 * spiring/context-crypto.xml에 설정
 * @author KMK
 *
 */
@Service("jnitCryptoService")
public class JnitCryptoServiceImpl extends EgovAbstractServiceImpl implements JnitCryptoService {

    /** 암호화서비스 */
    @Resource(name = "egovEnvCryptoService")
    EgovEnvCryptoService cryptoService;
    
    /**
     * 암호화(url인코딩포함)
     * @param str
     * @return
     */
	public String encrypt(String str) {
		return cryptoService.encrypt(str);
	}

    /**
     * 복호화(url인코딩포함)
     * @param str
     * @return
     */
	public String decrypt(String str) {
		return cryptoService.decrypt(str);
	}

    /**
     * 암호화
     * @param str
     * @return
     */
	public String encryptNoneEncoding(String str) {
		return cryptoService.encryptNone(str);
	}

    /**
     * 복호화
     * @param str
     * @return
     */
	public String decryptNoneEncoding(String str) {
		return cryptoService.decryptNone(str);
	}


    /**
     * 복호화(get parameter +기호 변환)
     * @param str
     * @return
     */
	public String decryptNoneEncodingParameter(String str) {
		if(str == null)return null;
		return cryptoService.decryptNone(str.replaceAll(" ", "+"));
	}
}
