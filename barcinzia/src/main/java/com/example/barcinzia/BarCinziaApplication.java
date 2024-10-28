package com.example.barcinzia;

import com.example.barcinzia.Entity.Image;
import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Enum.ItemType;
import com.example.barcinzia.Repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BarCinziaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BarCinziaApplication.class, args);

		ItemRepository itemRepository = context.getBean(ItemRepository.class);

		Item paninoCotoletta = new Item();
		Image paninoCotolettaImg = new Image();

		Item paninoPorchetta = new Item();
		Image paninoPorchettaImg = new Image();

		Item cocaColaZero = new Item();
		Image cocaColaZeroImg = new Image();

		paninoCotoletta.setName("Panino con la cotoletta");
		paninoCotoletta.setType(ItemType.valueOf("FOOD"));
		paninoCotoletta.setPrice(4.99);
		paninoCotolettaImg.setLink("https://www.aiafood.com/_next/image?url=https%3A%2F%2Fbackoffice.aiafood.com%2Fuploads%2Fxxl_panino_con_la_cotoletta_da_portare_ufficio_1d5f81b5b3.webp&w=1920&q=80");
		paninoCotolettaImg.setDescription("Panino con lattuga, pomodoro e cotoletta");
		paninoCotoletta.setImage(paninoCotolettaImg);

		paninoPorchetta.setName("Panino con la porchetta");
		paninoPorchetta.setType(ItemType.valueOf("FOOD"));
		paninoPorchetta.setPrice(6.50);
		paninoPorchettaImg.setLink("https://blog.giallozafferano.it/spuntidibonta/wp-content/uploads/2022/07/panino-con-porchetta.jpg");
		paninoPorchettaImg.setDescription("Panino con porchetta, scamorza e salsa bourguignonne");
		paninoPorchetta.setImage(paninoPorchettaImg);

		cocaColaZero.setName("Coca Cola Zero Zuccheri");
		cocaColaZero.setType(ItemType.valueOf("BEVERAGE"));
		cocaColaZero.setPrice(1.50);
		cocaColaZeroImg.setLink("https://d2f5fuie6vdmie.cloudfront.net/asset/imported/ita/2021/08/07/75f82f60f4e609d3cc52777f019202ffc4caf5e0.jpeg");
		cocaColaZeroImg.setDescription("Coca Cola Zero Zuccheri 100% gusto");
		cocaColaZero.setImage(cocaColaZeroImg);

		itemRepository.save(paninoCotoletta);
		itemRepository.save(paninoPorchetta);
		itemRepository.save(cocaColaZero);

	}

}
