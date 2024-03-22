package asint;

import asint.SintaxisAbstracta.*;

public interface Procesamiento {
    void procesa(SiDecs a);
    void procesa(NoDecs a);
    void procesa(MuchasDecs a);
    void procesa(UnaDec a);
    void procesa(SiParam a);
    void procesa(NoParam a);
    void procesa(UnParam a);
    void procesa(MuchosParams a);
    void procesa(TArray a);
    void procesa(TInt a);
    void procesa(TReal a);
    void procesa(TBool a);
    void procesa(TString a);
    void procesa(TIden a);
    void procesa(TStruct a);
    void procesa(SiExp a);
    void procesa(NoExp a);
    void procesa(UnaExp a);
    void procesa(MuchasExp a);
    void procesa(Suma a);
    void procesa(Resta a);
    void procesa(Array a);
    void procesa(ExpCampo a);
    void procesa(Punt a);
}
