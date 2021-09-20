import { by, element} from 'protractor';


export class TicketDelete{

    private navigateToDelete = element(by.xpath("//*[@id='linkEliminarTicket']"));
    private inputSearchIdClient = element(by.xpath("//*[@id='idClient']"));
    private searchClientDelete  = element(by.xpath("//button[contains(text(),'Buscar')]"));



    async navigateToDeletePage(){
        await this.navigateToDelete.click();
    }

    async sendKeyInputSearchId(){
        await this.inputSearchIdClient.sendKeys(1);
    }

    async clickSearchListForDelete(){
        await this.searchClientDelete.click();
    }

}