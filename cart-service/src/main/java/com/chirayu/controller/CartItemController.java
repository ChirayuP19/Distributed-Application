package com.chirayu.controller;

import com.chirayu.dto.CartItemRequestDto;
import com.chirayu.dto.CartItemResponseDto;
import com.chirayu.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponseDto addItemToCart(
            @Valid @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartItemService.addToCart(cartItemRequestDto);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItemResponseDto> getCartByUserID
            (@PathVariable("userId") String userId){
        return cartItemService.getUserCart(userId);
    }

    @DeleteMapping("/clear/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearUserCart(@PathVariable("userId") String  userId){
        cartItemService.clearCart(userId);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public CartItemResponseDto updateQuality(@RequestBody CartItemRequestDto cartItemRequestDto){
        return cartItemService.updateQuantity(cartItemRequestDto);
    }

    @DeleteMapping("/remove/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeItemFromCart(@PathVariable Long productId,@RequestParam String userId){
        cartItemService.removeItem(userId,productId);
    }
}
