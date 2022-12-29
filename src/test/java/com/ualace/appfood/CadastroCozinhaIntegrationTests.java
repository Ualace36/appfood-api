package com.ualace.appfood;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
class CadastroCozinhaIntegrationTests {
    @Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	@Test
	public void deveCadastrarCozinhaComSucesso_QuandoCozinhaCorreta() {
		// Test happyPath
		//TODO cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		//TODO ação
        novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

		//TODO validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
   // @Test
//	public void deveFalhar_quandoCadstrarCozinhaSemNome(){
//	//Test InHappyPath
//	//TODO CENÁRIO
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("");
//	//TODO AÇÃO
//     //novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
//
//	//TODO VALIDAÇÃO
//
//		ConstraintViolationException erroEsperado =
//				Assertions.assertThrows(ConstraintViolationException.class, () -> {
//					cadastroCozinhaService.salvar(novaCozinha);
//				});
//
//		assertThat(erroEsperado).isNotNull();
//	}

	@Test
	public void shouldFalhar_whenExcluirCozinhaEmUso(){
		//Test InHappyPath
		//TODO CENÁRIO
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.getId();
		//TODO AÇÃO
		EntidadeEmUsoException erroEsperado =
				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
					cadastroCozinhaService.excluir(2L);
				});

		assertThat(erroEsperado).isNotNull();

		//TODO VALIDAÇÃO
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente(){

		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () ->{
			cadastroCozinhaService.excluir(50l);
		});
	}

	}


