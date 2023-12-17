package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CatatanKeuanganTest {
    private AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("devicesName","Pixel 2 API 30");
        dc.setCapability("udid","emulator-5554");
        dc.setCapability("platformName","android");
        dc.setCapability("platformVersion","11");
        dc.setCapability("appPackage","com.chad.financialrecord");
        dc.setCapability("appActivity","com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url,dc);
    }
    @Test(priority = 1)
    public void menuTransaksi() {
        MobileElement menuTransaksi = (MobileElement) driver.findElementById("com.chad.financialrecord:id/fabMenu");
        menuTransaksi.click();
        System.out.println(menuTransaksi.getText());
        Assert.assertTrue(menuTransaksi.getText().contains("Pemasukan"));
    }
    @Test(priority = 2)
    public void pengeluaran(){
        MobileElement btnPengeluaran = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btnExpense");
        btnPengeluaran.click();
        delay(3);
        System.out.println(btnPengeluaran.getText());
        Assert.assertTrue(btnPengeluaran.getText().contains("Pengeluaran"));

        MobileElement tanggal = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        MobileElement klikTanggal = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc='05 Desember 2023']");
        MobileElement btnOk = (MobileElement) driver.findElementsById("android:id/button1");
        tanggal.click();
        klikTanggal.click();
        btnOk.click();
        delay(2);
        System.out.println(klikTanggal.getText());
        Assert.assertTrue(klikTanggal.getText().contains("05 Desember 2023"));

        //Kategori
        MobileElement btnKategori = (MobileElement) driver.findElementsById("com.chad.financialrecord:id/spCategory");
        btnKategori.click();
        delay(1);
        MobileElement makanan = (MobileElement) driver.findElementsByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvName' and @text='Makanan']");
        makanan.click();
        System.out.println(makanan.getText());
        Assert.assertTrue(makanan.getText().contains("pilih Makanan"));
        delay(1);

        MobileElement inputJumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        inputJumlah.sendKeys("50000");
        System.out.println(inputJumlah.getText());
        Assert.assertTrue(inputJumlah.getText().contains("50000"));

        MobileElement inputKeterangan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        inputKeterangan.sendKeys("Modal Awal Jualan Makanan");
        System.out.println(inputKeterangan.getText());
        Assert.assertTrue(inputKeterangan.getText().contains("Modal Awal Jualan Makanan"));

        MobileElement btnSimpan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");
        delay(2);
        btnSimpan.click();
        System.out.println(btnSimpan.getText());
        Assert.assertTrue(btnSimpan.getText().contains("Tersimpan"));
    }
    @Test(priority = 2)
    public void pemasukan(){
        menuTransaksi();
        MobileElement btnPemasukan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btnIncome");
        delay(3);
        btnPemasukan.click();
        System.out.println(btnPemasukan.getText());
        Assert.assertTrue(btnPemasukan.getText().contains("Pengeluaran"));

        MobileElement tanggal = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        MobileElement klikTanggal = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc='05 Desember 2023']");
        MobileElement btnOk = (MobileElement) driver.findElementsById("android:id/button1");
        tanggal.click();
        delay(1);
        klikTanggal.click();
        delay(2);
        btnOk.click();
        System.out.println(klikTanggal.getText());
        Assert.assertTrue(klikTanggal.getText().contains("05 Desember 2023"));

        //Kategori
        MobileElement kategoriPemasukan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/spCategory");
        delay(2);
        kategoriPemasukan.click();
        System.out.println(kategoriPemasukan.getText());
        Assert.assertTrue(kategoriPemasukan.getText().contains("Kategori Pemasukan"));

        MobileElement pilihPenjualan = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvName' and @text='Penjualan']");
        pilihPenjualan.click();
        delay(2);
        System.out.println(pilihPenjualan.getText());
        Assert.assertTrue(pilihPenjualan.getText().contains("Pilih Penjualan"));


        MobileElement inputJumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        inputJumlah.sendKeys("100000");
        System.out.println(inputJumlah.getText());
        Assert.assertTrue(inputJumlah.getText().contains("100000"));


        MobileElement inputKeterangan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        inputKeterangan.sendKeys("Hasil Penjualan");
        System.out.println(inputKeterangan.getText());
        Assert.assertTrue(inputKeterangan.getText().contains("Hasil Penjualan"));


        MobileElement btnSimpan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");
        btnSimpan.click();
        System.out.println(btnSimpan.getText());
        Assert.assertTrue(btnSimpan.getText().contains("Tersimpan"));
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }
    static void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
