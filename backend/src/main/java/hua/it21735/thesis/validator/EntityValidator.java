package hua.it21735.thesis.validator;

import hua.it21735.thesis.persistence.model.*;
import org.springframework.stereotype.Component;

@Component
public class EntityValidator {

    public void validateSize(Size size) {
        if (size == null) {
            throw new NullPointerException("Size cannot be null");
        }
        if (size.getGender() == null || size.getSize() == null) {
            throw new IllegalStateException("Size not valid");
        }
    }

    public void validateAddress(Address address) {
        if (address == null) {
            throw new NullPointerException("Address cannot be null");
        }
        if (address.getCity() == null || address.getNumber() == null || address.getStreet() == null || address.getPostCode() == null) {
            throw new IllegalStateException("Address not valid");
        }
    }

    public void validateCategory(Category category) {
        //todo
        //unique name, code + null id
    }

    public void validateColour(Colour colour) {
        //todo
        //unique code
        //check that it does not already exist
    }

    public void validateCustomer(Customer customer) {
    }
}
