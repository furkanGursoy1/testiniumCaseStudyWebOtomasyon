@reg
Feature: Zara alışveriş akışı testi

  @login @reg
  Scenario: Kulllanici Login olur
    Given Kullanıcı Zara ana sayfasına gider
    When Kullanıcı login olur

  @addbasket @reg
  Scenario: Ürün arama, sepete ekleme ve kontrol
    Given Kullanıcı Zara ana sayfasına gider
    And "ERKEK" menüsüne ve "TÜMÜNÜ GÖR" butonuna tıklar
    And Excel dosyasından birinci kelimeyi alarak arama kutusuna yazar
    And Arama kutusunu temizler
    And Excel dosyasından ikinci kelimeyi alarak arama kutusuna yazar
    And Klavyeden Enter tuşuna basar
    And Sonuçlardan rastgele bir ürün seçerek sepete ekler
    Then Seçilen ürün bilgisi ve fiyatı txt dosyasına yazılır
    And Ürün bilgileri ile sepetteki ürün bilgileri karşılaştırılır
    And Ürün adedi 2 yapılır ve doğruluğu doğrulanır
    Then Ürün sepetten silinir ve sepetin boş olduğu kontrol edilir

