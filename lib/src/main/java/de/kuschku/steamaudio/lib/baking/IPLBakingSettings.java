package de.kuschku.steamaudio.lib.baking;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLBakingSettings extends StructObject {
    static {
        BridJ.register();
    }

    public IPLBakingSettings() {
        super();
    }

    public IPLBakingSettings(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public boolean bakeParametric() {
        return this.io.getBooleanField(this, 0);
    }

    @Field(0)
    public IPLBakingSettings bakeParametric(boolean bakeParametric) {
        this.io.setBooleanField(this, 0, bakeParametric);
        return this;
    }

    @Field(1)
    public boolean bakeConvolution() {
        return this.io.getBooleanField(this, 1);
    }

    @Field(1)
    public IPLBakingSettings bakeConvolution(boolean bakeConvolution) {
        this.io.setBooleanField(this, 1, bakeConvolution);
        return this;
    }
}
