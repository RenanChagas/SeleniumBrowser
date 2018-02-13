package tests;

public class validation extends ValidatorHandler<Brick> implements Validator<Brick> {

    @Override
    public boolean validate(ValidatorContext context, Integer t) {
        if (t == 2) {
            context.addErrorMsg(String.format(CarError.SEATCOUNT_ERROR.msg(), t));
            return false;
        }
            return true;
        }
    }
