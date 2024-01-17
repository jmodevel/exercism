import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

class DnDCharacter {

    private static final int DICE_ROLLS      = 4;
    private static final int DICE_SIDES      = 6;
    private static final int SELECTED_SCORES = 3;

    private static final int    INITIAL_HITPOINTS  = 10;
    private static final int    MODIFIER_SUBSTRACT = 10;
    private static final double MODIFIER_DIVISOR   = 2.0;

    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;

    private Random dice = new Random();

    public DnDCharacter(){
        this.strength     = ability( rollDice() );
        this.dexterity    = ability( rollDice() );
        this.constitution = ability( rollDice() );
        this.intelligence = ability( rollDice() );
        this.wisdom       = ability( rollDice() );
        this.charisma     = ability( rollDice() );
    }

    int ability(List<Integer> scores) {
        return scores.stream()
                .sorted( Collections.reverseOrder() )
                .limit( SELECTED_SCORES )
                .mapToInt( i->i )
                .sum();
    }

    List<Integer> rollDice() {
        return IntStream.range( 0, DICE_ROLLS )
                .mapToObj( i-> dice.nextInt( DICE_SIDES )+1 )
                .toList();
    }

    int modifier(int input) {
        return (int) Math.floor( ( input - MODIFIER_SUBSTRACT ) / MODIFIER_DIVISOR );
    }

    int getStrength() {
        return this.strength;
    }

    int getDexterity() {
        return this.dexterity;
    }

    int getConstitution() {
        return this.constitution;
    }

    int getIntelligence() {
        return this.intelligence;
    }

    int getWisdom() {
        return this.wisdom;
    }

    int getCharisma() {
        return this.charisma;
    }

    int getHitpoints() {
        return INITIAL_HITPOINTS + modifier( getConstitution() );
    }

}
