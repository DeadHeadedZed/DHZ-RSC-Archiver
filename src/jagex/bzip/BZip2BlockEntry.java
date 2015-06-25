package jagex.bzip;

public class BZip2BlockEntry
{

    byte inputBuffer[];
    int offset;
    int compressedSize;
    int anInt566;
    int anInt567;
    byte outputBuffer[];
    int anInt569;
    int decompressedSize;
    int anInt571;
    int anInt572;
    byte aByte573;
    int anInt574;
    boolean wasRandomised;
    int anInt576;
    int anInt577;
    int blockSize100k;
    int anInt579;
    int origPtr;
    int anInt581;
    int anInt582;
    final int unzftab[] = new int[256];
    int anInt584;
    final int anIntArray585[] = new int[257];
    public static int ll8[];
    int inUseOffset;
    final boolean inUse[] = new boolean[256];
    final boolean inUse16[] = new boolean[16];
    final byte seqToUnseq[] = new byte[256];
    final byte yy[] = new byte[4096];
    final int anIntArray593[] = new int[16];
    final byte selector[] = new byte[18002];
    final byte selectorMtf[] = new byte[18002];
    final byte len[][] = new byte[6][258];
    final int limit[][] = new int[6][258];
    final int base[][] = new int[6][258];
    final int perm[][] = new int[6][258];
    final int minLens[] = new int[6];
    int anInt601;

    BZip2BlockEntry()
    {
    }
}
