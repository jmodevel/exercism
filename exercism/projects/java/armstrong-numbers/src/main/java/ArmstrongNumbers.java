class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        String num = String.valueOf( numberToCheck );
        return numberToCheck == num.chars()
                .map( d -> (int) Math.pow(Character.getNumericValue(d), num.length() ) )
                .sum();
    }

}
