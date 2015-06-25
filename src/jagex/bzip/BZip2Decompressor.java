package jagex.bzip;

import java.io.PrintStream;

public class BZip2Decompressor
{

    public static final int MAX_CODE_LEN = 23;
    private static final BZip2BlockEntry currentBlock = new BZip2BlockEntry();

    public BZip2Decompressor()
    {
    }

    public static int decompressBuffer(byte outputBuffer[], int decompressedSize, byte inputBuffer[], int compressedSize, int offset)
    {
        BZip2BlockEntry bzip2blockentry = currentBlock;
        currentBlock.inputBuffer = inputBuffer;
        currentBlock.offset = offset;
        currentBlock.outputBuffer = outputBuffer;
        currentBlock.anInt569 = 0;
        currentBlock.compressedSize = compressedSize;
        currentBlock.decompressedSize = decompressedSize;
        currentBlock.anInt577 = 0;
        currentBlock.anInt576 = 0;
        currentBlock.anInt566 = 0;
        currentBlock.anInt567 = 0;
        currentBlock.anInt571 = 0;
        currentBlock.anInt572 = 0;
        currentBlock.anInt579 = 0;
        readBlock(currentBlock);
        decompressedSize -= currentBlock.decompressedSize;
        return decompressedSize;
    }

    private static void method226(BZip2BlockEntry blockEntry)
    {
        byte byte4 = blockEntry.aByte573;
        int i = blockEntry.anInt574;
        int j = blockEntry.anInt584;
        int k = blockEntry.anInt582;
        BZip2BlockEntry _tmp = blockEntry;
        int ai[] = BZip2BlockEntry.ll8;
        int l = blockEntry.anInt581;
        byte abyte0[] = blockEntry.outputBuffer;
        int i1 = blockEntry.anInt569;
        int j1 = blockEntry.decompressedSize;
        int k1 = j1;
        int l1 = blockEntry.anInt601 + 1;
label0:
        do
        {
            if(i > 0)
            {
                do
                {
                    if(j1 == 0)
                    {
                        break label0;
                    }
                    if(i == 1)
                    {
                        break;
                    }
                    abyte0[i1] = byte4;
                    i--;
                    i1++;
                    j1--;
                } while(true);
                if(j1 == 0)
                {
                    i = 1;
                    break;
                }
                abyte0[i1] = byte4;
                i1++;
                j1--;
            }
            boolean flag = true;
            do
            {
                if(!flag)
                {
                    break;
                }
                flag = false;
                if(j == l1)
                {
                    i = 0;
                    break label0;
                }
                byte4 = (byte)k;
                l = ai[l];
                byte byte0 = (byte)(l & 0xff);
                l >>= 8;
                j++;
                if(byte0 != k)
                {
                    k = byte0;
                    if(j1 == 0)
                    {
                        i = 1;
                    } else
                    {
                        abyte0[i1] = byte4;
                        i1++;
                        j1--;
                        flag = true;
                        continue;
                    }
                    break label0;
                }
                if(j != l1)
                {
                    continue;
                }
                if(j1 == 0)
                {
                    i = 1;
                    break label0;
                }
                abyte0[i1] = byte4;
                i1++;
                j1--;
                flag = true;
            } while(true);
            i = 2;
            l = ai[l];
            byte byte1 = (byte)(l & 0xff);
            l >>= 8;
            if(++j != l1)
            {
                if(byte1 != k)
                {
                    k = byte1;
                } else
                {
                    i = 3;
                    l = ai[l];
                    byte byte2 = (byte)(l & 0xff);
                    l >>= 8;
                    if(++j != l1)
                    {
                        if(byte2 != k)
                        {
                            k = byte2;
                        } else
                        {
                            l = ai[l];
                            byte byte3 = (byte)(l & 0xff);
                            l >>= 8;
                            j++;
                            i = (byte3 & 0xff) + 4;
                            l = ai[l];
                            k = (byte)(l & 0xff);
                            l >>= 8;
                            j++;
                        }
                    }
                }
            }
        } while(true);
        int i2 = blockEntry.anInt571;
        blockEntry.anInt571 += k1 - j1;
        if(blockEntry.anInt571 < i2)
        {
            blockEntry.anInt572++;
        }
        blockEntry.aByte573 = byte4;
        blockEntry.anInt574 = i;
        blockEntry.anInt584 = j;
        blockEntry.anInt582 = k;
        BZip2BlockEntry _tmp1 = blockEntry;
        BZip2BlockEntry.ll8 = ai;
        blockEntry.anInt581 = l;
        blockEntry.outputBuffer = abyte0;
        blockEntry.anInt569 = i1;
        blockEntry.decompressedSize = j1;
    }

    private static void readBlock(BZip2BlockEntry blockEntry)
    {
        int minLens_zt = 0;
        int limit_zt[] = null;
        int base_zt[] = null;
        int perm_zt[] = null;
        blockEntry.blockSize100k = 1;
        BZip2BlockEntry _tmp = blockEntry;
        if(BZip2BlockEntry.ll8 == null)
        {
            BZip2BlockEntry _tmp1 = blockEntry;
            BZip2BlockEntry.ll8 = new int[blockEntry.blockSize100k * 0x186a0];
        }
        for(boolean flag19 = true; flag19; flag19 = blockEntry.anInt584 == blockEntry.anInt601 + 1 && blockEntry.anInt574 == 0)
        {
            byte tmpRegister = getUByte(blockEntry);
            if(tmpRegister == 23)
            {
                return;
            }
            getUByte(blockEntry);
            getUByte(blockEntry);
            getUByte(blockEntry);
            getUByte(blockEntry);
            getUByte(blockEntry);
            blockEntry.anInt579++;
            getUByte(blockEntry);
            getUByte(blockEntry);
            getUByte(blockEntry);
            getUByte(blockEntry);
            tmpRegister = getBit(blockEntry);
            blockEntry.wasRandomised = tmpRegister != 0;
            if(blockEntry.wasRandomised)
            {
                System.out.println("PANIC! RANDOMISED BLOCK!");
            }
            blockEntry.origPtr = 0;
            tmpRegister = getUByte(blockEntry);
            blockEntry.origPtr = blockEntry.origPtr << 8 | tmpRegister & 0xff;
            tmpRegister = getUByte(blockEntry);
            blockEntry.origPtr = blockEntry.origPtr << 8 | tmpRegister & 0xff;
            tmpRegister = getUByte(blockEntry);
            blockEntry.origPtr = blockEntry.origPtr << 8 | tmpRegister & 0xff;
            for(int j = 0; j < 16; j++)
            {
                byte byte1 = getBit(blockEntry);
                blockEntry.inUse16[j] = byte1 == 1;
            }

            for(int k = 0; k < 256; k++)
            {
                blockEntry.inUse[k] = false;
            }

            for(int l = 0; l < 16; l++)
            {
                if(!blockEntry.inUse16[l])
                {
                    continue;
                }
                for(int i3 = 0; i3 < 16; i3++)
                {
                    byte byte2 = getBit(blockEntry);
                    if(byte2 == 1)
                    {
                        blockEntry.inUse[l * 16 + i3] = true;
                    }
                }

            }

            createMaps(blockEntry);
            int alphaSize = blockEntry.inUseOffset + 2;
            int groupCount = getBits(3, blockEntry);
            int selectorCount = getBits(15, blockEntry);
            for(int selectorCounter = 0; selectorCounter < selectorCount; selectorCounter++)
            {
                int j3 = 0;
                do
                {
                    byte byte3 = getBit(blockEntry);
                    if(byte3 == 0)
                    {
                        break;
                    }
                    j3++;
                } while(true);
                blockEntry.selectorMtf[selectorCounter] = (byte)j3;
            }

            byte pos[] = new byte[6];
            for(byte v = 0; v < groupCount; v++)
            {
                pos[v] = v;
            }

            for(int j1 = 0; j1 < selectorCount; j1++)
            {
                byte v = blockEntry.selectorMtf[j1];
                byte tmp = pos[v];
                for(; v > 0; v--)
                {
                    pos[v] = pos[v - 1];
                }

                pos[0] = tmp;
                blockEntry.selector[j1] = tmp;
            }

            for(int t = 0; t < groupCount; t++)
            {
                int curr = getBits(5, blockEntry);
                for(int i = 0; i < alphaSize; i++)
                {
                    do
                    {
                        byte tmp = getBit(blockEntry);
                        if(tmp == 0)
                        {
                            break;
                        }
                        tmp = getBit(blockEntry);
                        if(tmp == 0)
                        {
                            curr++;
                        } else
                        {
                            curr--;
                        }
                    } while(true);
                    blockEntry.len[t][i] = (byte)curr;
                }

            }

            for(int t = 0; t < groupCount; t++)
            {
                byte minLen = 32;
                int maxLen = 0;
                for(int i = 0; i < alphaSize; i++)
                {
                    if(blockEntry.len[t][i] > maxLen)
                    {
                        maxLen = blockEntry.len[t][i];
                    }
                    if(blockEntry.len[t][i] < minLen)
                    {
                        minLen = blockEntry.len[t][i];
                    }
                }

                createDecodeTables(blockEntry.limit[t], blockEntry.base[t], blockEntry.perm[t], blockEntry.len[t], minLen, maxLen, alphaSize);
                blockEntry.minLens[t] = minLen;
            }

            int eob = blockEntry.inUseOffset + 1;
            int limitLast = 0x186a0 * blockEntry.blockSize100k;
            int lastShadow = -1;
            int groupPos = 0;
            for(int i2 = 0; i2 <= 255; i2++)
            {
                blockEntry.unzftab[i2] = 0;
            }

            int j9 = 4095;
            for(int l8 = 15; l8 >= 0; l8--)
            {
                for(int i9 = 15; i9 >= 0; i9--)
                {
                    blockEntry.yy[j9] = (byte)(l8 * 16 + i9);
                    j9--;
                }

                blockEntry.anIntArray593[l8] = j9 + 1;
            }

            int i6 = 0;
            if(groupPos == 0)
            {
                lastShadow++;
                groupPos = 50;
                byte zt = blockEntry.selector[lastShadow];
                minLens_zt = blockEntry.minLens[zt];
                limit_zt = blockEntry.limit[zt];
                perm_zt = blockEntry.perm[zt];
                base_zt = blockEntry.base[zt];
            }
            groupPos--;
            int zn = minLens_zt;
            int zvec;
            byte thebit;
            for(zvec = getBits(zn, blockEntry); zvec > limit_zt[zn]; zvec = zvec << 1 | thebit)
            {
                zn++;
                thebit = getBit(blockEntry);
            }

            for(int nextSym = perm_zt[zvec - base_zt[zn]]; nextSym != eob;)
            {
                if(nextSym == 0 || nextSym == 1)
                {
                    int j6 = -1;
                    int k6 = 1;
                    do
                    {
                        if(nextSym == 0)
                        {
                            j6 += k6;
                        } else
                        if(nextSym == 1)
                        {
                            j6 += 2 * k6;
                        }
                        k6 *= 2;
                        if(groupPos == 0)
                        {
                            lastShadow++;
                            groupPos = 50;
                            byte byte13 = blockEntry.selector[lastShadow];
                            minLens_zt = blockEntry.minLens[byte13];
                            limit_zt = blockEntry.limit[byte13];
                            perm_zt = blockEntry.perm[byte13];
                            base_zt = blockEntry.base[byte13];
                        }
                        groupPos--;
                        int zn2 = minLens_zt;
                        int zvec2;
                        byte thebit2;
                        for(zvec2 = getBits(zn2, blockEntry); zvec2 > limit_zt[zn2]; zvec2 = zvec2 << 1 | thebit2)
                        {
                            zn2++;
                            thebit2 = getBit(blockEntry);
                        }

                        nextSym = perm_zt[zvec2 - base_zt[zn2]];
                    } while(nextSym == 0 || nextSym == 1);
                    j6++;
                    byte ch = blockEntry.seqToUnseq[blockEntry.yy[blockEntry.anIntArray593[0]] & 0xff];
                    blockEntry.unzftab[ch & 0xff] += j6;
                    while(j6 > 0) 
                    {
                        BZip2BlockEntry _tmp2 = blockEntry;
                        BZip2BlockEntry.ll8[i6] = ch & 0xff;
                        i6++;
                        j6--;
                    }
                } else
                {
                    int j11 = nextSym - 1;
                    byte tmp;
                    if(j11 < 16)
                    {
                        int j10 = blockEntry.anIntArray593[0];
                        tmp = blockEntry.yy[j10 + j11];
                        for(; j11 > 3; j11 -= 4)
                        {
                            int k11 = j10 + j11;
                            blockEntry.yy[k11] = blockEntry.yy[k11 - 1];
                            blockEntry.yy[k11 - 1] = blockEntry.yy[k11 - 2];
                            blockEntry.yy[k11 - 2] = blockEntry.yy[k11 - 3];
                            blockEntry.yy[k11 - 3] = blockEntry.yy[k11 - 4];
                        }

                        for(; j11 > 0; j11--)
                        {
                            blockEntry.yy[j10 + j11] = blockEntry.yy[(j10 + j11) - 1];
                        }

                        blockEntry.yy[j10] = tmp;
                    } else
                    {
                        int l10 = j11 / 16;
                        int i11 = j11 % 16;
                        int k10 = blockEntry.anIntArray593[l10] + i11;
                        tmp = blockEntry.yy[k10];
                        for(; k10 > blockEntry.anIntArray593[l10]; k10--)
                        {
                            blockEntry.yy[k10] = blockEntry.yy[k10 - 1];
                        }

                        blockEntry.anIntArray593[l10]++;
                        for(; l10 > 0; l10--)
                        {
                            blockEntry.anIntArray593[l10]--;
                            blockEntry.yy[blockEntry.anIntArray593[l10]] = blockEntry.yy[(blockEntry.anIntArray593[l10 - 1] + 16) - 1];
                        }

                        blockEntry.anIntArray593[0]--;
                        blockEntry.yy[blockEntry.anIntArray593[0]] = tmp;
                        if(blockEntry.anIntArray593[0] == 0)
                        {
                            int i10 = 4095;
                            for(int k9 = 15; k9 >= 0; k9--)
                            {
                                for(int l9 = 15; l9 >= 0; l9--)
                                {
                                    blockEntry.yy[i10] = blockEntry.yy[blockEntry.anIntArray593[k9] + l9];
                                    i10--;
                                }

                                blockEntry.anIntArray593[k9] = i10 + 1;
                            }

                        }
                    }
                    blockEntry.unzftab[blockEntry.seqToUnseq[tmp & 0xff] & 0xff]++;
                    BZip2BlockEntry _tmp3 = blockEntry;
                    BZip2BlockEntry.ll8[i6] = blockEntry.seqToUnseq[tmp & 0xff] & 0xff;
                    i6++;
                    if(groupPos == 0)
                    {
                        lastShadow++;
                        groupPos = 50;
                        byte byte14 = blockEntry.selector[lastShadow];
                        minLens_zt = blockEntry.minLens[byte14];
                        limit_zt = blockEntry.limit[byte14];
                        perm_zt = blockEntry.perm[byte14];
                        base_zt = blockEntry.base[byte14];
                    }
                    groupPos--;
                    int k7 = minLens_zt;
                    int j8;
                    byte byte11;
                    for(j8 = getBits(k7, blockEntry); j8 > limit_zt[k7]; j8 = j8 << 1 | byte11)
                    {
                        k7++;
                        byte11 = getBit(blockEntry);
                    }

                    nextSym = perm_zt[j8 - base_zt[k7]];
                }
            }

            blockEntry.anInt574 = 0;
            blockEntry.aByte573 = 0;
            blockEntry.anIntArray585[0] = 0;
            for(int j2 = 1; j2 <= 256; j2++)
            {
                blockEntry.anIntArray585[j2] = blockEntry.unzftab[j2 - 1];
            }

            for(int k2 = 1; k2 <= 256; k2++)
            {
                blockEntry.anIntArray585[k2] += blockEntry.anIntArray585[k2 - 1];
            }

            for(int l2 = 0; l2 < i6; l2++)
            {
                BZip2BlockEntry _tmp4 = blockEntry;
                byte byte7 = (byte)(BZip2BlockEntry.ll8[l2] & 0xff);
                BZip2BlockEntry _tmp5 = blockEntry;
                BZip2BlockEntry.ll8[blockEntry.anIntArray585[byte7 & 0xff]] |= l2 << 8;
                blockEntry.anIntArray585[byte7 & 0xff]++;
            }

            BZip2BlockEntry _tmp6 = blockEntry;
            blockEntry.anInt581 = BZip2BlockEntry.ll8[blockEntry.origPtr] >> 8;
            blockEntry.anInt584 = 0;
            BZip2BlockEntry _tmp7 = blockEntry;
            blockEntry.anInt581 = BZip2BlockEntry.ll8[blockEntry.anInt581];
            blockEntry.anInt582 = (byte)(blockEntry.anInt581 & 0xff);
            blockEntry.anInt581 >>= 8;
            blockEntry.anInt584++;
            blockEntry.anInt601 = i6;
            method226(blockEntry);
        }

    }

    private static byte getUByte(BZip2BlockEntry blockEntry)
    {
        return (byte)getBits(8, blockEntry);
    }

    private static byte getBit(BZip2BlockEntry blockEntry)
    {
        return (byte)getBits(1, blockEntry);
    }

    private static int getBits(int i, BZip2BlockEntry blockEntry)
    {
        int j;
        do
        {
            if(blockEntry.anInt577 >= i)
            {
                int k = blockEntry.anInt576 >> blockEntry.anInt577 - i & (1 << i) - 1;
                blockEntry.anInt577 -= i;
                j = k;
                break;
            }
            blockEntry.anInt576 = blockEntry.anInt576 << 8 | blockEntry.inputBuffer[blockEntry.offset] & 0xff;
            blockEntry.anInt577 += 8;
            blockEntry.offset++;
            blockEntry.compressedSize--;
            blockEntry.anInt566++;
            if(blockEntry.anInt566 == 0)
            {
                blockEntry.anInt567++;
            }
        } while(true);
        return j;
    }

    private static void createMaps(BZip2BlockEntry blockEntry)
    {
        blockEntry.inUseOffset = 0;
        for(int i = 0; i < 256; i++)
        {
            if(blockEntry.inUse[i])
            {
                blockEntry.seqToUnseq[blockEntry.inUseOffset] = (byte)i;
                blockEntry.inUseOffset++;
            }
        }

    }

    private static void createDecodeTables(int limit[], int base[], int perm[], byte length[], int minLen, int maxLen, int alphaSize)
    {
        int pp = 0;
        for(int i = minLen; i <= maxLen; i++)
        {
            for(int j = 0; j < alphaSize; j++)
            {
                if(length[j] == i)
                {
                    perm[pp] = j;
                    pp++;
                }
            }

        }

        for(int j1 = 0; j1 < 23; j1++)
        {
            base[j1] = 0;
        }

        for(int k1 = 0; k1 < alphaSize; k1++)
        {
            base[length[k1] + 1]++;
        }

        for(int l1 = 1; l1 < 23; l1++)
        {
            base[l1] += base[l1 - 1];
        }

        for(int i2 = 0; i2 < 23; i2++)
        {
            limit[i2] = 0;
        }

        int vec = 0;
        for(int j2 = minLen; j2 <= maxLen; j2++)
        {
            vec += base[j2 + 1] - base[j2];
            limit[j2] = vec - 1;
            vec <<= 1;
        }

        for(int k2 = minLen + 1; k2 <= maxLen; k2++)
        {
            base[k2] = (limit[k2 - 1] + 1 << 1) - base[k2];
        }

    }

}
