import { by, element} from 'protractor';

export class MoviePage{
    private linkMovie = element(by.xpath("//a[@href='/movie']"));
    private routeLink = element(by.xpath("//a[@href='/movie/list']"));
    private buttomBy = element(by.xpath("//button//span"));
    private labelTimeMovie = element(by.xpath("//div[@class='d-flex justify-content-between']//child::div[1]"));
    private labelTime = element(by.xpath("//div[@class='d-flex justify-content-between']//child::div[2]"))
    private textMovie = element(by.xpath("//h3[@class='heading']"));
    async clickLinkMovie(){
        await this.linkMovie.click();
    }
    
    async clickRouteLinkMovieList(){
        await this.routeLink.click();
    }

    verifyButtomBuy(){
        expect(this.buttomBy.getText()).toEqual("Comprar");
    }

    verifyLabelTimeMovie(){
        expect(this.labelTimeMovie.getText()).toEqual("Tiempo pelicula");
    }

    verifyLabelTime(){
        expect(this.labelTime.getText()).toEqual("140 minutos");
    }

    verifyMovieName(){
        expect(this.textMovie.getText()).toEqual("Joker");
    }

    async clickButtomBuy(){
        await this.buttomBy.click();
    }

}