package br.com.tt.petshop.client;

import br.com.tt.petshop.dto.CreditoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "credito-api", url = "https://imersao-credit0-api.herokuapp.com/")
public interface CreditoApiFeignClient extends CreditoApiClient{

    @Override
    @GetMapping("/credito/{cpf}")
    CreditoDto verificaSituacao(@PathVariable("cpf") String cpf);

}
