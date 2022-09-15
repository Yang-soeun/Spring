package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);//클래스로 넘어오는 타입이랑 Item 타입이 같은지 확인
        //자식 클래스도 가능
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;//casting

        //검증 로직
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        //if(!StringUtils.hasText(item.getItemName())) {//글자가 없는 경우
        //    bindingResult.rejectValue("itemName", "required");
        //}

        if(item.getPrice() == null || item.getPrice() <1000 || item.getPrice() >1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity()==null ||item.getQuantity()> 9999){
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 1000){
                errors.reject("totalPriceMin", new Object[]{1000, resultPrice}, null);
            }
        }
    }
}
