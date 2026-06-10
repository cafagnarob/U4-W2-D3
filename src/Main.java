import entities.Customer;
import entities.Order;
import entities.Product;

public void main(String[] args) {
    Random random = new Random();

// magazino

    Product libroDellaGiungla = new Product(1L, "il libro della giungla", "book", 19.99);
    Product aladin = new Product(2L, "Aladin", "book", 15.99);
    Product laBibbia = new Product(3L, "La Bibbia", "book", 199.99);
    Product laSteleDiRosetta = new Product(4L, "La stele di Rosetta", "book", 1999.99);
    Product hercules = new Product(5L, "Hercules", "film", 39.99);
    Product laPanteraNera = new Product(6L, "La pantera nera", "film", 15.99);
    Product laDisfidaDiBarletta = new Product(7L, "La Disfida di Barletta", "film", 9.99);
    Product ilPianetaDelTesoro = new Product(8L, "Il pianeta del tesoro", "film", 69.99);
    Product pannolini = new Product(9L, "Pannolini", "Baby", 69.99);
    Product giochi = new Product(10L, "Giochi", "Baby", 29.99);
    Product pomata = new Product(11L, "Pomata", "Baby", 89.99);
    Product biglietti = new Product(12L, "Biglietti", "Baby", 169.99);
    Product bigliettiCalcio = new Product(13L, "Roma-Milan", "Boys", 139.99);
    Product fucile = new Product(14L, "Ak-47", "Boys", 339.99);
    Product moto = new Product(15L, "Ducati", "Boys", 10139.99);


    List<Product> listaProdotti = new ArrayList<>(List.of(laBibbia,
            ilPianetaDelTesoro, libroDellaGiungla, aladin, laSteleDiRosetta,
            hercules, laPanteraNera, laDisfidaDiBarletta, pannolini, giochi,
            pomata, biglietti, moto, bigliettiCalcio, fucile));


    //clienti

    Customer marco = new Customer(50L, "Marco", 0);
    Customer giada = (new Customer(51L, "Giada", 1));
    Customer alessia = (new Customer(52L, "Alessia", 2));
    Customer luana = (new Customer(53L, "Luana", 2));
    Customer alfredo = (new Customer(54L, "ALfredo", 1));
    Customer matteo = (new Customer(55L, "Matteo", 2));

    List<Customer> listaClienti = new ArrayList<>(List.of(marco, giada, alessia, luana, alfredo, matteo));


    //ORDINI

    List<Product> ordine1 = new ArrayList<>(List.of(ilPianetaDelTesoro, pannolini, hercules));
    List<Product> ordine2 = new ArrayList<>(List.of(laPanteraNera, laDisfidaDiBarletta, biglietti));
    List<Product> ordine3 = new ArrayList<>(List.of(laBibbia, laSteleDiRosetta, fucile));
    List<Product> ordine4 = new ArrayList<>(List.of(biglietti, pomata, moto));
    List<Order> listaOrdini = new ArrayList<>();

    listaOrdini.add(new Order(70L, "completato", LocalDate.of(2025, 1, 31), LocalDate.now(), ordine1, marco));
    listaOrdini.add(new Order(71L, "in corso", LocalDate.of(2026, 2, 4), LocalDate.now(), ordine2, matteo));
    listaOrdini.add(new Order(72L, "completo", LocalDate.of(2025, 3, 28), LocalDate.now(), ordine3, alessia));
    listaOrdini.add(new Order(73L, "in corso", LocalDate.of(2026, 7, 21), LocalDate.now(), ordine4, alfredo));

    System.out.println("*********** MAGAZINO ************");
    System.out.println(listaProdotti);
    System.out.println("*********** CLIENTI ************");
    System.out.println(listaClienti);
    System.out.println("*********** ORDINI ************");
    System.out.println(listaOrdini);


    System.out.println("*********** LISTA LIBRI PREZZO MAGGIORE DI 100 ************");

    List<Product> listaLibriMax100 = listaProdotti.stream()
            .filter(product -> product.getCategory().equals("book") && product.getPrice() > 100).toList();

    listaLibriMax100.forEach(System.out::println);


    System.out.println("*********** LISTA ORDINI CON PRODOTTI BABY ************");

    List<Order> listaOrdiniBaby = listaOrdini.stream()
            .filter(order -> order.getProducts().stream()
                    .anyMatch(product -> product.getCategory().equals("Baby")))
            .toList();

    listaOrdiniBaby.forEach(System.out::println);


    System.out.println("*********** LISTA PRODOTTI CON PRODOTTI BOYS ************");

    List<Product> listaPerBoys = listaProdotti.stream().filter(product -> product.getCategory().equals("Boys")).toList();

    listaPerBoys.forEach(product -> product.setPrice(product.getPrice() * 0.9));

    System.out.println(listaPerBoys);


    System.out.println("*********** LISTA PRODOTTI DI T2  ************");

    LocalDate dataInizio = LocalDate.of(2025, 1, 1);
    LocalDate dataFine = LocalDate.of(2026, 1, 1);

    List<Order> listaDiOrdiniT2 = listaOrdini.stream()
            .filter(order -> order.getCustomer().getTier() >= 2).toList();


    List<Order> listaOrdiniT2Intervallo = listaDiOrdiniT2.stream()
            .filter(order -> order.getOrderDate().isAfter(dataInizio) && order.getOrderDate().isBefore(dataFine))
            .toList();


    List<Product> listaProdottiFinale = listaOrdiniT2Intervallo.stream()
            .flatMap(order -> order.getProducts().stream())
            .toList();

    System.out.println(listaProdottiFinale);


}
