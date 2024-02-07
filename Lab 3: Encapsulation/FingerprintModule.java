public class FingerprintModule {
    private String fingerprintSerialNumber;
    private String fingerprint;

    public FingerprintModule(String fingerprintSerialNumber){
        this.fingerprintSerialNumber = fingerprintSerialNumber;
    }

    public void registerFingerprint(String fingerprint){
        this.fingerprint = fingerprint;
    }

    public boolean checkFingerprint(String fingerprint){
        if(this.fingerprint==fingerprint){
            return true;
        }
        else{
            return false;
        }
    }

    public String getfingerprint(){
        return fingerprint;
    }

    public String getFingerprintSerialNumber(){
        return fingerprintSerialNumber;
    }
}
