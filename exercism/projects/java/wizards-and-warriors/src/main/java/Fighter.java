abstract class Fighter {

    boolean isVulnerable() {
        return false;
    }

    abstract int damagePoints(Fighter fighter);

    @Override
    public String toString() {
        return String.format( "Fighter is a %s", this.getClass().getName() );
    }

}

class Warrior extends Fighter {

    private static final int VULNERABLE_DAMAGE_POINTS = 10;
    private static final int DAMAGE_POINTS            = 6;

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    int damagePoints(Fighter fighter) {
        return fighter.isVulnerable()? VULNERABLE_DAMAGE_POINTS : DAMAGE_POINTS;
    }
}

class Wizard extends Fighter {

    private static final int SPELL_DAMAGE_PONTS = 12;
    private static final int DAMAGE_PONTS       = 3;

    private boolean spellPrepared = false;

    @Override
    boolean isVulnerable() {
        return !spellPrepared;
    }

    @Override
    int damagePoints(Fighter fighter) {
        return spellPrepared? SPELL_DAMAGE_PONTS : DAMAGE_PONTS;
    }

    void prepareSpell() {
        this.spellPrepared = true;
    }

}
