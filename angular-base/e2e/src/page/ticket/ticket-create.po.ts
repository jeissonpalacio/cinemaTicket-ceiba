import { by, element} from 'protractor';

export class TicketPage{
 
    //li[@class='list-group-item session-item'][1]
    private listElementMovieProjector =element(by.xpath("//*[@class='card-body text-dark']/descendant::li[1]"));
    private selectActive = element(by.xpath("//*[@ng-reflect-ng-class='active'][1]"));
    private inputIdClient = element(by.xpath("//input[@placeholder='Id del cliente']"));
    private buttomcreate = element(by.xpath("//button[@class='btn btn-primary']"));
    private succesTicket = element(by.xpath("//div[@role='alert']//strong[1]"));

    async clickListElementMovieProjector(){
        await this.listElementMovieProjector.click();
    }

    async clickSelectActive(){
        await this.selectActive.click();
    }

    async sendKeysInputClient(){
        await this.inputIdClient.sendKeys(1);
    }
    
    async clickButtomCreate(){
        await this.buttomcreate.isEnabled();
        await this.buttomcreate.click()
    }

    async succesTicketElement(){
        await this.succesTicket.isEnabled();
        expect(this.succesTicket.getText()).toEqual("Ticket comprado exitosamente!");
    }
}