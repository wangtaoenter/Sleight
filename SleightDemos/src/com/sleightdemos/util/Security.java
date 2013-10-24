/*
 * 文件名: Security.java
 * 版    权：   
 * 描    述: 加解密相关
 * 创建人: w00138133
 * 创建时间:2011-8-27
 */
package com.sleightdemos.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

/**
 * 
 * Security 提供了一个安全算法类,其中包括DES AES对称密码算法和SHA散列算法.
 * 
 * @author w00138133
 * @version [V1.0, 2011-8-27]
 */
public final class Security
{
    private static final String TAG = "Security";

    private Security()
    {

    }

    /**
     * DES对称加密方法
     * 
     * @param byteSource 需要加密的数据
     * @return 经过加密的数据
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws Exception
     */
    public static byte[] symmetricEncryptoDES(byte[] byteSource)
        throws IllegalBlockSizeException, BadPaddingException, IOException,
        NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
        NoSuchPaddingException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int mode = Cipher.ENCRYPT_MODE;
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        byte[] keyData = {1, 9, 8, 2, 0, 8, 2, 2 };
        DESKeySpec keySpec = new DESKeySpec(keyData);
        Key key = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, key);
        int blockSize = cipher.getBlockSize();
        int position = 0;
        int length = byteSource.length;

        boolean more = true;
        while (more)
        {
            if (position + blockSize <= length)
            {
                baos.write(cipher.update(byteSource, position, blockSize));
                position += blockSize;
            }
            else
            {
                more = false;
            }
        }

        if (position < length)
        {
            baos.write(cipher.doFinal(byteSource, position, length - position));
        }
        else
        {
            baos.write(cipher.doFinal());
        }

        return baos.toByteArray();
    }

    /**
     * DES对称解密方法.
     * 
     * @param byteSource 需要解密的数据
     * 
     * @return 经过解密的数据
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * 
     * @throws Exception the exception
     */
    public static byte[] symmetricDecryptoDES(byte[] byteSource)
        throws NoSuchAlgorithmException, InvalidKeyException,
        InvalidKeySpecException, NoSuchPaddingException, IOException,
        IllegalBlockSizeException, BadPaddingException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int mode = Cipher.DECRYPT_MODE;
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        byte[] keyData = {1, 9, 8, 2, 0, 8, 2, 2 };
        DESKeySpec keySpec = new DESKeySpec(keyData);
        Key key = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, key);
        int blockSize = cipher.getBlockSize();
        int position = 0;
        int length = byteSource.length;

        boolean more = true;
        while (more)
        {
            if (position + blockSize <= length)
            {
                baos.write(cipher.update(byteSource, position, blockSize));
                position += blockSize;
            }
            else
            {
                more = false;
            }
        }
        if (position < length)
        {
            baos.write(cipher.doFinal(byteSource, position, length - position));
        }
        else
        {
            baos.write(cipher.doFinal());
        }
        return baos.toByteArray();
    }

    /**
     * DES加密对外接口.
     * 
     * @param pwOfString the pw of string
     * 
     * @return the string
     */
    public static String symmetricEncryptoDES(String pwOfString)
    {
        String output = null;
        if (null != pwOfString)
        {
            try
            {
                byte[] afterChange = symmetricEncryptoDES(pwOfString
                    .getBytes("UTF8"));
                output = new String(Base64.encode(afterChange, Base64.DEFAULT));
                return output;
            }
            catch (Exception e)
            {
                Log.e(TAG, "symmetricEncryptoDES error:" + e.getMessage());
                return null;
            }
        }
        return output;
    }

    /**
     * DES解密对外接口.
     * 
     * @param input the input
     * 
     * @return the string
     */
    public static String symmetricDecryptoDES(String input)
    {
        String output = null;
        if (null != input)
        {
            try
            {
                byte[] sourceByte = Base64.decode(input.getBytes(),
                    Base64.DEFAULT);
                //解密并转换成字符串
                output = new String(symmetricDecryptoDES(sourceByte), "UTF8");
            }
            catch (Exception e)
            {
                Log.e(TAG, "symmetricDecryptoDES error:" + e.getMessage());
                return null;
            }
        }
        return output;
    }

    /**
     * MD5加密.
     * 
     * @param s the s
     * 
     * @return the string
     */
    public static String md5(String s)
    {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };
        try
        {
            byte[] strTemp = s.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(strTemp);
            byte[] md = messageDigest.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return String.valueOf(str);
        }
        catch (Exception e)
        {
            Log.e(TAG, "md5 error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 散列算法.
     * 
     * @param byteSource 需要散列计算的数据
     * 
     * @return 经过散列计算的数据
     * @throws NoSuchAlgorithmException
     */
    public static byte[] hashMethod(byte[] byteSource)
        throws NoSuchAlgorithmException
    {
        MessageDigest currentAlgorithm = MessageDigest.getInstance("SHA-1");
        currentAlgorithm.reset();
        currentAlgorithm.update(byteSource);
        return currentAlgorithm.digest();
    }

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     * 
     * @param strSrc 要加密的字符串
     * @return 加密后string
     */
    public static String getSha256(String strSrc)
    {
        MessageDigest md = null;
        String strDes = null;
        String encName = null;

        byte[] bt = strSrc.getBytes();
        try
        {
            encName = "SHA-256";
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return strDes;
    }

    /**
     * 字节数组到十六进制转换
     * 
     * @param bts 字节数组
     * @return 结果字符串
     */
    public static String bytes2Hex(byte[] bts)
    {
        StringBuilder des = new StringBuilder("");
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1)
            {
                des = des.append("0");
            }
            des = des.append(tmp);
        }
        return des.toString();
    }

    /**
     * 获取默认密钥KeySpec，密钥为softclient
     * 
     * @return KeySpec
     */
    public static SecretKeySpec getKeySpecForAES()
    {
        //use bytes of "softclient" as the key
        byte[] bytes = {(byte) 0x73, (byte) 0x6f, (byte) 0x66, (byte) 0x74,
            (byte) 0x63, (byte) 0x6c, (byte) 0x69, (byte) 0x65, (byte) 0x6e,
            (byte) 0x74 };

        SecretKeySpec spec = new SecretKeySpec(bytes, "AES");
        return spec;
    }

    /**
     * AES加密对外接口.使用默认密码softclient
     * 
     * @param text 要加密的文本
     * @return 结果字符串
     */
    public static String symmetricEncryptoAES(String text)
    {
        return symmetricEncryptoAES(text, getKeySpecForAES());
    }

    /**
     * AES加密对外接口.
     * 
     * @param text 要加密的文本
     * @param spec 密钥
     * @return 结果字符串
     */
    public static String symmetricEncryptoAES(String text, SecretKeySpec spec)
    {
        if (null == text || "".equals(text))
        {
            return null;
        }
        ByteArrayOutputStream pw = new ByteArrayOutputStream();
        try
        {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, spec);

            byte[] byteSource = text.getBytes("UTF8");

            int blockSize = cipher.getBlockSize();
            int position = 0;
            int length = byteSource.length;

            boolean more = true;
            while (more)
            {
                if (position + blockSize <= length)
                {
                    pw.write(cipher.update(byteSource, position, blockSize));
                    position += blockSize;
                }
                else
                {
                    more = false;
                }
            }

            if (position < length)
            {
                pw.write(cipher
                    .doFinal(byteSource, position, length - position));
            }
            else
            {
                pw.write(cipher.doFinal());
            }

            return new String(Base64.encode(pw.toByteArray(), Base64.DEFAULT));
        }
        catch (Exception e)
        {
            Log.e(TAG, "symmetricDecryptoAES error:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                pw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * AES解密对外接口.使用默认密码softclient
     * 
     * @param text 要解密的文本
     * @return 结果字符串
     */
    public static String symmetricDecryptoAES(String text)
    {
        return symmetricDecryptoAES(text, getKeySpecForAES());
    }

    /**
     * AES解密对外接口.使用默认密码softclient
     * 
     * @param text 要解密的文本
     * @param spec 密钥
     * @return 结果字符串
     */
    public static String symmetricDecryptoAES(String text, SecretKeySpec spec)
    {
        if (null == text || "".equals(text))
        {
            return null;
        }

        ByteArrayOutputStream pw = new ByteArrayOutputStream();
        try
        {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, spec);

            byte[] byteSource = Base64.decode(text.getBytes("UTF8"),
                Base64.DEFAULT);
            int blockSize = cipher.getBlockSize();
            int position = 0;
            int length = byteSource.length;

            boolean more = true;
            while (more)
            {
                if (position + blockSize <= length)
                {
                    pw.write(cipher.update(byteSource, position, blockSize));
                    position += blockSize;
                }
                else
                {
                    more = false;
                }
            }
            if (position < length)
            {
                pw.write(cipher
                    .doFinal(byteSource, position, length - position));
            }
            else
            {
                pw.write(cipher.doFinal());
            }

            return new String(pw.toByteArray(), "UTF8");
        }
        catch (Exception e)
        {
            Log.e(TAG, "symmetricDecryptoAES error:" + e.getMessage());
            return null;
        }
        finally
        {
            try
            {
                pw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
