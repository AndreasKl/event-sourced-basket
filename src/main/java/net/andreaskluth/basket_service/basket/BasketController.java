package net.andreaskluth.basket_service.basket;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.andreaskluth.basket_service.basket.commands.AddLineItemCommand;
import net.andreaskluth.basket_service.basket.commands.CreateBasketCommand;
import net.andreaskluth.basket_service.basket.query.ReadBasket;
import net.andreaskluth.basket_service.basket.query.ReadBasketRepository;
import net.andreaskluth.basket_service.infrastructure.POST;

@RestController
public class BasketController {

    private final CommandBus bus;
    private final ReadBasketRepository repository;

    @Autowired
    public BasketController(CommandBus commandBus, ReadBasketRepository repository) {
        this.bus = commandBus;
        this.repository = repository;
    }

    @POST(path = { "/baskets" })
    public ReadBasket create() {
        CreateBasketCommand createBasketCommand = new CreateBasketCommand();
        bus.dispatch(GenericCommandMessage.asCommandMessage(createBasketCommand));

        return myBasket(createBasketCommand.id());
    }

    @POST(path = { "/baskets/{id}" })
    public ReadBasket add(@PathVariable("id") String id, @RequestBody AddLineItem add) {
        AddLineItemCommand lineItemCommand = new AddLineItemCommand(id, add.productId, add.productName, add.priceInCent,
                add.quantity);
        bus.dispatch(GenericCommandMessage.asCommandMessage(lineItemCommand));

        return myBasket(id);

    }

    @RequestMapping(path = { "/", "/baskets" }, method = RequestMethod.GET)
    public ModelAndView list(Pageable pageable) {
        return new ModelAndView("list", "baskets", repository.findAll(pageable));
    }

    private ReadBasket myBasket(String id) {
        return repository.findOne(id).orElseThrow(() -> new BasketNotFoundException());
    }

    public static class AddLineItem {
        public String productId, productName;
        public int priceInCent, quantity;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class BasketNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public BasketNotFoundException() {
            super("basket does not exist");
        }
    }
}