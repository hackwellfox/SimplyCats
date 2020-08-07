package com.github.mnesikos.simplycats.entity;

import com.github.mnesikos.simplycats.SimplyCats;
import com.github.mnesikos.simplycats.entity.core.Genetics.*;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Optional;

public class AbstractCatSCEntity extends TameableEntity {
    private static final TrackedData<String> EYE_COLOR = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> FUR_LENGTH = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> EUMELANIN = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> PHAEOMELANIN = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> DILUTION = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> DILUTE_MOD = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> AGOUTI = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> TABBY = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> SPOTTED = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> TICKED = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> COLORPOINT = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_0 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_1 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_2 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_PAWS_0 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_PAWS_1 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_PAWS_2 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<String> WHITE_PAWS_3 = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private final String[] whiteTexturesArray = new String[3];
    private final String[] whitePawTexturesArray = new String[4];
    
    private String texturePrefix;
    private final String[] catTexturesArray = new String[12];

    private static final TrackedData<Optional<BlockPos>> HOME_POSITION = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.OPTIONAL_BLOCK_POS);
    public static final TrackedData<String> OWNER_NAME = DataTracker.registerData(AbstractCatSCEntity.class, TrackedDataHandlerRegistry.STRING);
    private boolean PURR;
    private int PURR_TIMER;
    
    public AbstractCatSCEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        setPhenotype();
    }

    @Override
    public EntityData initialize(WorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CompoundTag entityTag) {
        //this.setPhenotype();
        return super.initialize(world, difficulty, spawnReason, entityData, entityTag);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(EYE_COLOR, EyeColor.COPPER.toString());
        this.dataTracker.startTracking(FUR_LENGTH, "L-L");
        this.dataTracker.startTracking(EUMELANIN, "B-B");
        this.dataTracker.startTracking(PHAEOMELANIN, "Xo-Xo");
        this.dataTracker.startTracking(DILUTION, "D-D");
        this.dataTracker.startTracking(DILUTE_MOD, "dm-dm");
        this.dataTracker.startTracking(AGOUTI, "a-a");
        this.dataTracker.startTracking(TABBY, "Mc-Mc");
        this.dataTracker.startTracking(SPOTTED, "sp-sp");
        this.dataTracker.startTracking(TICKED, "ta-ta");
        this.dataTracker.startTracking(COLORPOINT, "C-C");
        this.dataTracker.startTracking(WHITE, "w-w");
        this.dataTracker.startTracking(WHITE_0, "");
        this.dataTracker.startTracking(WHITE_1, "");
        this.dataTracker.startTracking(WHITE_2, "");
        this.dataTracker.startTracking(WHITE_PAWS_0, "");
        this.dataTracker.startTracking(WHITE_PAWS_1, "");
        this.dataTracker.startTracking(WHITE_PAWS_2, "");
        this.dataTracker.startTracking(WHITE_PAWS_3, "");

        this.dataTracker.startTracking(HOME_POSITION, Optional.empty());
        this.dataTracker.startTracking(OWNER_NAME, "");
    }

    public void setPhenotype() {
        this.setGenotype(FUR_LENGTH, FurLength.init() + "-" + FurLength.init());
        this.setGenotype(EUMELANIN, Eumelanin.init() + "-" + Eumelanin.init());
        this.setGenotype(PHAEOMELANIN, Phaeomelanin.init());
        this.setGenotype(DILUTION, Dilution.init() + "-" + Dilution.init());
        this.setGenotype(DILUTE_MOD, DiluteMod.init() + "-" + DiluteMod.init());
        this.setGenotype(AGOUTI, Agouti.init() + "-" + Agouti.init());
        this.setGenotype(TABBY, Tabby.init() + "-" + Tabby.init());
        this.setGenotype(SPOTTED, Spotted.init() + "-" + Spotted.init());
        this.setGenotype(TICKED, Ticked.init() + "-" + Ticked.init());
        this.setGenotype(COLORPOINT, Colorpoint.init() + "-" + Colorpoint.init());
        this.setGenotype(WHITE, White.init() + "-" + White.init());
        this.selectWhiteMarkings();
        this.setGenotype(EYE_COLOR, selectEyeColor());
    }

    private String selectEyeColor() {
        String color = EyeColor.init(random.nextInt(4));
        // todo change whiteCheck == White.Spotting to a better check for proper white face check
        if (getWhite().contains(White.DOMINANT.getAllele()))
            color = EyeColor.init(random.nextInt(5));
        if (getPhenotype(COLORPOINT).equalsIgnoreCase(Colorpoint.COLORPOINT.toString()))
            color = EyeColor.init(4);
        return color;
    }

    void selectWhiteMarkings() {
        int base;
        int body = 0;
        int face = 0;
        int tail = 0;

        for (int j = 0; j <= 3; j++) {
            this.whitePawTexturesArray[j] = "";
            this.setWhitePawTextures(j, "");
        }

        switch (this.getWhite()) {
            case "Wd-Wd": case "Wd-w": case "Wd-Ws":
            case "w-Wd": case "Ws-Wd":
                base = 6;
                body = 1;
                face = 0;
                tail = 0;
                break;

            case "w-w":
                base = 0;
                body = 0;
                face = 0;
                tail = 0;
                break;

            case "Ws-Ws":
                base = random.nextInt(2) + 4; //4-5
                if (base == 5) {
                    body = random.nextInt(4) + 1;
                    face = random.nextInt(6) + 1;
                    if (body > 1)
                        tail = random.nextInt(3) + 1;
                }
                else if (base == 4) {
                    body = 1;
                    face = random.nextInt(5) + 1;
                }
                if (random.nextInt(10) == 0) { //10% chance for solid white
                    base = 6;
                    body = 1;
                    face = 0;
                    tail = 0;
                }
                break;

            case "Ws-w": case "w-Ws":
                base = random.nextInt(3) + 1; //1-3
                body = 1;
                if (base == 2 || base == 3)
                    this.selectWhitePaws(base);
                if (base == 3)
                    face = random.nextInt(5) + 1;
                break;

            default:
                throw new IllegalArgumentException("Error selecting white markings; " + this.getWhite());
        }

        this.whiteTexturesArray[0] = body == 0 ? "" : "white_" + base + "_body" + body;
        this.setWhiteTextures(0, whiteTexturesArray[0]);

        this.whiteTexturesArray[1] = face == 0 ? "" : "white_" + (base == 3 || base == 4 ? 34 : base) + "_face" + face;
        this.setWhiteTextures(1, whiteTexturesArray[1]);

        this.whiteTexturesArray[2] = tail == 0 ? "" : "white_" + base + "_tail" + tail;
        this.setWhiteTextures(2, whiteTexturesArray[2]);
    }

    private void selectWhitePaws(int base) {
        /*
         * boolean all is set so all 4 paws are white
         * making it more common than only random 1-4 white paws
         */
        boolean all = random.nextInt(4) <= 2;

        if (all || random.nextInt(4) <= 2) {
            this.whitePawTexturesArray[0] = "white_" + base + "_paw1";
            this.setWhitePawTextures(0, whitePawTexturesArray[0]);
        }

        if (all || random.nextInt(4) <= 2) {
            this.whitePawTexturesArray[1] = "white_" + base + "_paw2";
            this.setWhitePawTextures(1, whitePawTexturesArray[1]);
        }

        if (all || random.nextInt(4) <= 2) {
            this.whitePawTexturesArray[2] = "white_" + base + "_paw3";
            this.setWhitePawTextures(2, whitePawTexturesArray[2]);
        }

        if (all || random.nextInt(4) <= 2) {
            this.whitePawTexturesArray[3] = "white_" + base + "_paw4";
            this.setWhitePawTextures(3, whitePawTexturesArray[3]);
        }
    }

    private String getWhiteTextures(int i) {
        switch (i) {
            case 0:
                return this.dataTracker.get(WHITE_0);
            case 1:
                return this.dataTracker.get(WHITE_1);
            case 2:
                return this.dataTracker.get(WHITE_2);
            default:
                return "";
        }
    }

    private void setWhiteTextures(int i, String value) {
        switch (i) {
            case 0:
                this.dataTracker.set(WHITE_0, value);
                break;
            case 1:
                this.dataTracker.set(WHITE_1, value);
                break;
            case 2:
                this.dataTracker.set(WHITE_2, value);
                break;
        }
    }

    private String getWhitePawTextures(int i) {
        switch (i) {
            case 0:
                return this.dataTracker.get(WHITE_PAWS_0);
            case 1:
                return this.dataTracker.get(WHITE_PAWS_1);
            case 2:
                return this.dataTracker.get(WHITE_PAWS_2);
            case 3:
                return this.dataTracker.get(WHITE_PAWS_3);
            default:
                return "";
        }
    }

    private void setWhitePawTextures(int i, String value) {
        switch (i) {
            case 0:
                this.dataTracker.set(WHITE_PAWS_0, value);
                break;
            case 1:
                this.dataTracker.set(WHITE_PAWS_1, value);
                break;
            case 2:
                this.dataTracker.set(WHITE_PAWS_2, value);
                break;
            case 3:
                this.dataTracker.set(WHITE_PAWS_3, value);
                break;
        }
    }

    /*private String getGenotype(TrackedData<String> parameter) {
        return this.dataTracker.get(parameter);
    }*/

    void setGenotype(TrackedData<String> parameter, String value) {
        this.dataTracker.set(parameter, value);
    }

    public String getFurLength() {
        return this.dataTracker.get(FUR_LENGTH);
    }

    public String getEumelanin() {
        return this.dataTracker.get(EUMELANIN);
    }

    public String getPhaeomelanin() {
        return this.dataTracker.get(PHAEOMELANIN);
    }

    public String getDilution() {
        return this.dataTracker.get(DILUTION);
    }

    public String getDiluteMod() {
        return this.dataTracker.get(DILUTE_MOD);
    }

    public String getAgouti() {
        return this.dataTracker.get(AGOUTI);
    }

    public String getTabby() {
        return this.dataTracker.get(TABBY);
    }

    public String getSpotted() {
        return this.dataTracker.get(SPOTTED);
    }

    public String getTicked() {
        return this.dataTracker.get(TICKED);
    }

    public String getColorpoint() {
        return this.dataTracker.get(COLORPOINT);
    }

    public String getWhite() {
        return this.dataTracker.get(WHITE);
    }

    public String getEyeColor() {
        return this.dataTracker.get(EYE_COLOR);
    }

    public Sex getSex() {
        return this.dataTracker.get(PHAEOMELANIN).contains(Phaeomelanin.MALE.getAllele()) ? Sex.MALE : Sex.FEMALE;
    }

    public boolean hasHomePos() {
        return this.dataTracker.get(HOME_POSITION).isPresent();
    }

    public BlockPos getHomePos() {
        return this.dataTracker.get(HOME_POSITION).orElse(new BlockPos(this.getPos()));
    }

    public void setHomePos(BlockPos position) {
        this.dataTracker.set(HOME_POSITION, Optional.of(position));
    }

    public void resetHomePos() {
        this.dataTracker.set(HOME_POSITION, Optional.empty());
    }

    public Text getOwnerName() {
        if (this.getOwner() != null)
            return this.getOwner().getDisplayName();
        else if (!this.dataTracker.get(OWNER_NAME).isEmpty())
            return new LiteralText(this.dataTracker.get(OWNER_NAME));
        else if (this.getOwnerUuid() != null)
            return new TranslatableText("entity.simplycats.cat.unknown_owner");
        else
            return new TranslatableText("entity.simplycats.cat.untamed");
    }

    public void setOwnerName(String name) {
        this.dataTracker.set(OWNER_NAME, name);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putString("EyeColor", this.getEyeColor());
        compound.putString("FurLength", this.getFurLength());
        compound.putString("Eumelanin", this.getEumelanin());
        compound.putString("Phaeomelanin", this.getPhaeomelanin());
        compound.putString("Dilution", this.getDilution());
        compound.putString("DiluteMod", this.getDiluteMod());
        compound.putString("Agouti", this.getAgouti());
        compound.putString("Tabby", this.getTabby());
        compound.putString("Spotted", this.getSpotted());
        compound.putString("Ticked", this.getTicked());
        compound.putString("Colorpoint", this.getColorpoint());
        compound.putString("White", this.getWhite());
        for (int i = 0; i <= 2; i++)
            compound.putString("White_" + i, this.getWhiteTextures(i));
        for (int i = 0; i <= 3; i++)
            compound.putString("WhitePaws_" + i, this.getWhitePawTextures(i));
        if (this.hasHomePos()) {
            compound.putInt("HomePosX", this.getHomePos().getX());
            compound.putInt("HomePosY", this.getHomePos().getY());
            compound.putInt("HomePosZ", this.getHomePos().getZ());
        }
        compound.putString("OwnerName", this.dataTracker.get(OWNER_NAME));
    }

    @Override
    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        this.setGenotype(EYE_COLOR, compound.getString("EyeColor"));
        this.setGenotype(FUR_LENGTH, compound.getString("FurLength"));
        this.setGenotype(EUMELANIN, compound.getString("Eumelanin"));
        this.setGenotype(PHAEOMELANIN, compound.getString("Phaeomelanin"));
        this.setGenotype(DILUTION, compound.getString("Dilution"));
        this.setGenotype(DILUTE_MOD, compound.getString("DiluteMod"));
        this.setGenotype(AGOUTI, compound.getString("Agouti"));
        this.setGenotype(TABBY, compound.getString("Tabby"));
        this.setGenotype(SPOTTED, compound.getString("Spotted"));
        this.setGenotype(TICKED, compound.getString("Ticked"));
        this.setGenotype(COLORPOINT, compound.getString("Colorpoint"));
        this.setGenotype(WHITE, compound.getString("White"));
        for (int i = 0; i <= 2; i++)
            this.setWhiteTextures(i, compound.getString("White_" + i));
        for (int i = 0; i <= 3; i++)
            this.setWhitePawTextures(i, compound.getString("WhitePaws_" + i));
        if (compound.contains("HomePosX"))
            this.setHomePos(new BlockPos(compound.getInt("HomePosX"), compound.getInt("HomePosY"), compound.getInt("HomePosZ")));
        this.setOwnerName(compound.getString("OwnerName"));
    }

    public String getPhenotype(TrackedData<String> dataParameter) {
        if (dataParameter == FUR_LENGTH)
            return FurLength.getPhenotype(this.getFurLength());
        else if (dataParameter == EUMELANIN)
            return Eumelanin.getPhenotype(this.getEumelanin());
        else if (dataParameter == PHAEOMELANIN)
            return Phaeomelanin.getPhenotype(this.getPhaeomelanin());
        else if (dataParameter == DILUTION)
            return Dilution.getPhenotype(this.getDilution());
        else if (dataParameter == DILUTE_MOD)
            return DiluteMod.getPhenotype(this.getDiluteMod());
        else if (dataParameter == AGOUTI)
            return Agouti.getPhenotype(this.getAgouti());
        else if (dataParameter == TABBY)
            return Tabby.getPhenotype(this.getTabby());
        else if (dataParameter == SPOTTED)
            return Spotted.getPhenotype(this.getSpotted());
        else if (dataParameter == TICKED)
            return Ticked.getPhenotype(this.getTicked());
        else if (dataParameter == COLORPOINT)
            return Colorpoint.getPhenotype(this.getColorpoint());
        else if (dataParameter == WHITE)
            return White.getPhenotype(this.getWhite());
        else // EYES
            return this.getEyeColor();
    }

    private void resetTexturePrefix() {
        this.texturePrefix = null;
    }

    /*@SideOnly(Side.CLIENT)
    private void setCatTexturePaths() {
        String solid = this.getPhenotype(EUMELANIN);
        if (this.getPhenotype(PHAEOMELANIN).equalsIgnoreCase(Phaeomelanin.RED.toString().toLowerCase()))
            solid = this.getPhenotype(PHAEOMELANIN);
        if (this.getPhenotype(DILUTION).equalsIgnoreCase(Dilution.DILUTE.toString().toLowerCase())) {
            solid = solid + "_" + this.getPhenotype(DILUTION);
            *//*if (this.getPhenotype(DILUTE_MOD).equalsIgnoreCase(DiluteMod.CARAMELIZED.toString().toLowerCase()))
                solid = solid + "_" + this.getPhenotype(DILUTE_MOD);*//*
        }

        String tabby = this.getPhenotype(TABBY) + "_" + solid;
        if (this.getGenotype(SPOTTED).contains(Spotted.SPOTTED.getAllele()))
            tabby = this.getPhenotype(SPOTTED) + "_" + tabby;
        if (this.getPhenotype(TICKED).equalsIgnoreCase(Ticked.TICKED.toString().toLowerCase()))
            tabby = this.getPhenotype(TICKED) + "_" + solid;

        String tortie = "";
        if (this.getPhenotype(PHAEOMELANIN).equalsIgnoreCase(Phaeomelanin.TORTOISESHELL.toString().toLowerCase())) {
            tortie = this.getPhenotype(PHAEOMELANIN) + "_" + (tabby.replace(("_" + solid), ""));
            if (this.getPhenotype(DILUTION).equalsIgnoreCase(Dilution.DILUTE.toString().toLowerCase())) {
                tortie = tortie + "_" + this.getPhenotype(DILUTION);
                *//*if (this.getPhenotype(DILUTE_MOD).equalsIgnoreCase(DiluteMod.CARAMELIZED.toString().toLowerCase()))
                    tortie = tortie + "_" + this.getPhenotype(DILUTE_MOD);*//*
            }
        }

        if (!this.getPhenotype(PHAEOMELANIN).equalsIgnoreCase(Phaeomelanin.RED.toString()) && this.getPhenotype(AGOUTI).equalsIgnoreCase(Agouti.SOLID.toString().toLowerCase()))
            tabby = "";

        String colorpoint = "";
        if (!this.getPhenotype(COLORPOINT).equalsIgnoreCase(Colorpoint.NOT_POINTED.toString().toLowerCase())) {
            colorpoint = this.getPhenotype(COLORPOINT);
            if (!tabby.equals("") && !this.getPhenotype(PHAEOMELANIN).equalsIgnoreCase(Phaeomelanin.RED.toString()))
                colorpoint = colorpoint + "_" + "tabby";
            else if (solid.equalsIgnoreCase(Eumelanin.BLACK.toString()))
                colorpoint = colorpoint + "_" + solid;
            else if (this.getPhenotype(PHAEOMELANIN).equalsIgnoreCase(Phaeomelanin.RED.toString()))
                colorpoint = colorpoint + "_red";
            if (!tortie.equals(""))
                tortie = tortie + "_point";
        }

        this.catTexturesArray[0] = Ref.MODID + ":textures/entity/cat/solid/" + solid + ".png";
        this.catTexturesArray[1] = tabby.equals("") ? null : (Ref.MODID + ":textures/entity/cat/tabby/" + tabby + ".png");
        this.catTexturesArray[2] = tortie.equals("") ? null : (Ref.MODID + ":textures/entity/cat/tortie/" + tortie + ".png");
        this.catTexturesArray[3] = colorpoint.equals("") ? null : (Ref.MODID + ":textures/entity/cat/colorpoint/" + colorpoint + ".png");
        this.catTexturesArray[4] = this.getWhiteTextures(0).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhiteTextures(0) + ".png");
        this.catTexturesArray[5] = this.getWhiteTextures(1).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhiteTextures(1) + ".png");
        this.catTexturesArray[6] = this.getWhiteTextures(2).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhiteTextures(2) + ".png");
        this.catTexturesArray[7] = this.getWhitePawTextures(0).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhitePawTextures(0) + ".png");
        this.catTexturesArray[8] = this.getWhitePawTextures(1).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhitePawTextures(1) + ".png");
        this.catTexturesArray[9] = this.getWhitePawTextures(2).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhitePawTextures(2) + ".png");
        this.catTexturesArray[10] = this.getWhitePawTextures(3).equals("") ? null : (Ref.MODID + ":textures/entity/cat/white/new/" + this.getWhitePawTextures(3) + ".png");
        this.catTexturesArray[11] = Ref.MODID + ":textures/entity/cat/eyes/" + this.getPhenotype(EYE_COLOR) + ".png";
        this.texturePrefix = "cat/" + solid + tabby + tortie + colorpoint +
                this.getWhiteTextures(0) + this.getWhiteTextures(1) + this.getWhiteTextures(2) +
                this.getWhitePawTextures(0) + this.getWhitePawTextures(1) +
                this.getWhitePawTextures(2) + this.getWhitePawTextures(3) +
                getPhenotype(EYE_COLOR);
        // todo System.out.println(this.texturePrefix);
    }

    @SideOnly(Side.CLIENT)
    public String getCatTexture() {
        if (this.texturePrefix == null)
            this.setCatTexturePaths();

        return this.texturePrefix;
    }

    @SideOnly(Side.CLIENT)
    public String[] getTexturePaths() {
        if (this.texturePrefix == null)
            this.setCatTexturePaths();

        return this.catTexturesArray;
    }*/

    @Override
    public void baseTick() {
        super.baseTick();

        if (this.PURR) {
            if (PURR_TIMER == 0) {
                this.PURR = false;
                this.PURR_TIMER = 0;
            }
        }

        if (this.age % 40 == 0) {
            if (!this.world.isClient && this.getOwner() != null)
                this.setOwnerName(this.getOwner().getDisplayName().asString());
        }

        if (this.world.isClient && this.dataTracker.isDirty()) {
            this.dataTracker.clearDirty();
            this.resetTexturePrefix();
        }
    }

    @Override
    protected void mobTick() {
        super.mobTick();

        if(this.getHealth() <= 0 && this.isTamed() && this.getOwner() == null) {
            this.deathTime = 0;
            this.setHealth(1);
        }

        if (this.PURR && PURR_TIMER > 0) {
            --PURR_TIMER;
        }
    }

    public boolean canBeTamed(PlayerEntity player) {
        return /*(SCConfig.TAMED_LIMIT == 0 || player.getEntityData().getInteger("CatCount") < SCConfig.TAMED_LIMIT) &&*/ !this.isTamed();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.PURR && this.random.nextInt(10) == 0) { // 1/10th chance an interaction will result in purrs
            this.PURR = true;
            this.PURR_TIMER = (this.random.nextInt(61) + 30) * 20; // random range of 600 to 1800 ticks (0.5 to 1.5 IRL minutes)
        }

        return ActionResult.SUCCESS;
    }

    /** A custom setTamed method to set the owner's data along with taming or untaming a cat.
     * @param tamed - true is tamed, false is untamed, used for this.setTamed(tamed) call at the end.
     * @param owner - the EntityPlayer who is taming the cat.
     */
    public void setTamed(boolean tamed, PlayerEntity owner) {
//        int catCount = owner.getEntityData().getInteger("CatCount");
        if (tamed) {
            /*owner.getEntityData().setInteger("CatCount", catCount + 1);
            if (!world.isClient)
                CHANNEL.sendTo(new SCNetworking(catCount + 1), (EntityPlayerMP) owner);*/
            this.setOwnerName(owner.getName().asString());

        } else {
            /*owner.getEntityData().setInteger("CatCount", catCount - 1);
            if (!world.isClient)
                CHANNEL.sendTo(new SCNetworking(catCount - 1), (EntityPlayerMP) owner);*/
            this.setOwnerName("");
        }

        this.setTamed(tamed);
    }

    @Override
    public void onDeath(DamageSource cause) {
        /*if (this.isTamed() && this.getOwner() != null) {
            int count = this.getOwner().getEntityData().getInteger("CatCount");
            this.getOwner().getEntityData().setInteger("CatCount", count - 1);
            if (!world.isClient)
                CHANNEL.sendTo(new SCNetworking(count - 1), (EntityPlayerMP) this.getOwner());
        }*/
        super.onDeath(cause);
    }

    @Override
    public PassiveEntity createChild(PassiveEntity parFather) {
        DataTracker father = parFather.getDataTracker();
        DataTracker mother = this.getDataTracker();
        CatSCEntity child = new CatSCEntity(SimplyCats.CAT, this.world);

        String[] matFur = mother.get(FUR_LENGTH).split("-");
        String[] patFur = father.get(FUR_LENGTH).split("-");
        String fur = matFur[random.nextInt(2)] + "-" + patFur[random.nextInt(2)];

        String[] matEum = mother.get(EUMELANIN).split("-");
        String[] patEum = father.get(EUMELANIN).split("-");
        String eum = matEum[random.nextInt(2)] + "-" + patEum[random.nextInt(2)];

        String[] matPhae = mother.get(PHAEOMELANIN).split("-");
        String[] patPhae = father.get(PHAEOMELANIN).split("-");
        String phae = matPhae[random.nextInt(2)] + "-" + patPhae[random.nextInt(2)];

        String[] matDil = mother.get(DILUTION).split("-");
        String[] patDil = father.get(DILUTION).split("-");
        String dil = matDil[random.nextInt(2)] + "-" + patDil[random.nextInt(2)];

        String[] matDilm = mother.get(DILUTE_MOD).split("-");
        String[] patDilm = father.get(DILUTE_MOD).split("-");
        String dilm = matDilm[random.nextInt(2)] + "-" + patDilm[random.nextInt(2)];

        String[] matAgo = mother.get(AGOUTI).split("-");
        String[] patAgo = father.get(AGOUTI).split("-");
        String ago = matAgo[random.nextInt(2)] + "-" + patAgo[random.nextInt(2)];

        String[] matTab = mother.get(TABBY).split("-");
        String[] patTab = father.get(TABBY).split("-");
        String tab = matTab[random.nextInt(2)] + "-" + patTab[random.nextInt(2)];

        String[] matSpot = mother.get(SPOTTED).split("-");
        String[] patSpot = father.get(SPOTTED).split("-");
        String spot = matSpot[random.nextInt(2)] + "-" + patSpot[random.nextInt(2)];

        String[] matTick = mother.get(TICKED).split("-");
        String[] patTick = father.get(TICKED).split("-");
        String tick = matTick[random.nextInt(2)] + "-" + patTick[random.nextInt(2)];

        String[] matPoint = mother.get(COLORPOINT).split("-");
        String[] patPoint = father.get(COLORPOINT).split("-");
        String point = matPoint[random.nextInt(2)] + "-" + patPoint[random.nextInt(2)];

        String[] matWhite = mother.get(WHITE).split("-");
        String[] patWhite = father.get(WHITE).split("-");
        String white = matWhite[random.nextInt(2)] + "-" + patWhite[random.nextInt(2)];

        child.setGenotype(FUR_LENGTH, fur);
        child.setGenotype(EUMELANIN, eum);
        child.setGenotype(PHAEOMELANIN, phae);
        child.setGenotype(DILUTION, dil);
        child.setGenotype(DILUTE_MOD, dilm);
        child.setGenotype(AGOUTI, ago);
        child.setGenotype(TABBY, tab);
        child.setGenotype(SPOTTED, spot);
        child.setGenotype(TICKED, tick);
        child.setGenotype(COLORPOINT, point);
        child.setGenotype(WHITE, white);
        child.selectWhiteMarkings();

        int eyesMin;
        int eyesMax;
        int matEye = EyeColor.valueOf(mother.get(EYE_COLOR).toUpperCase()).ordinal();
        int patEye = EyeColor.valueOf(father.get(EYE_COLOR).toUpperCase()).ordinal();
        if (matEye > patEye) {
            eyesMin = patEye - 1;
            eyesMax = matEye;
        } else {
            eyesMin = matEye - 1;
            eyesMax = patEye;
        }
        eyesMin = eyesMin < 0 ? 0 : eyesMin;
        if (white.contains(White.DOMINANT.getAllele()))
            eyesMax = 4;
        else
            eyesMax = eyesMax >= 4 ? (eyesMin < 3 ? eyesMin+1 : 3) : eyesMax;
        int eyes = random.nextInt((eyesMax - eyesMin) + 1) + eyesMin;
        String eye = EyeColor.init(matEye == 4 && patEye == 4 ? (eyesMax == 4 ? 4 : random.nextInt(4)) : eyes);
        if (point.contentEquals(Colorpoint.COLORPOINT.getAllele() + "-" + Colorpoint.COLORPOINT.getAllele()))
            eye = EyeColor.init(4);

        child.setGenotype(EYE_COLOR, eye);

        if (this.isTamed() && this.getOwnerUuid() != null) { // checks if mother is tamed & her owner's UUID exists
            PlayerEntity owner = this.world.getPlayerByUuid(this.getOwnerUuid()); // grabs owner by UUID
            if (owner != null && child.canBeTamed(owner)) { // checks if owner is not null (is online), and is able to tame the kitten OR if the tame limit is disabled
                child.setTamed(this.isTamed(), owner); // sets tamed by owner
                child.setOwnerUuid(this.getOwnerUuid()); // idk if this is needed, don't feel like testing it
                child.setOwner(owner);
                if (this.hasHomePos()) // checks mother's home point
                    child.setHomePos(this.getHomePos()); // sets kitten's home point to mother's
            }
        }

        return child;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    public boolean isAngry() {
        return ((this.dataTracker.get(TAMEABLE_FLAGS)) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte b0 = this.dataTracker.get(TAMEABLE_FLAGS);

        if (angry)
            this.dataTracker.set(TAMEABLE_FLAGS, (byte)(b0 | 2));
        else
            this.dataTracker.set(TAMEABLE_FLAGS, (byte)(b0 & -3));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            if (this.random.nextInt(10) == 0)
                return SoundEvents.ENTITY_CAT_HISS;
            else
                return null;
        } else if (this.isInLove() || this.PURR) {
            return SoundEvents.ENTITY_CAT_PURR;
        } else {
            if (this.random.nextInt(10) == 0) {
                if (this.random.nextInt(10) == 0)
                    return SoundEvents.ENTITY_CAT_PURREOW;
                else
                    return SoundEvents.ENTITY_CAT_AMBIENT;
            }
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public Text getName() {
        if (this.hasCustomName())
            return this.getCustomName();
        else
            return this.isTamed() ? new TranslatableText("entity.Cat.name").formatted() : super.getName();
    }
}