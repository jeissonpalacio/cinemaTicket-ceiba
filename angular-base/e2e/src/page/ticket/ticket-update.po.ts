import { by, element} from 'protractor';

export class TicketUpdate{
 
    private navigateToEdit = element(by.xpath("//*[@href='/ticket/edit']"));
    private inputSearchIdClient = element(by.xpath("//*[@id='idClient']"));
    private upgradeButton = element(by.xpath("(//*[contains(text(),' Actualizar')])[1]"));
    private updateButton  = element(by.xpath("//button[contains(text(),'Editar')]"));
    private selectSeat = element(by.xpath("//select[@id='ticketSeat']"));
    private selectOption = element(by.xpath("//option[not(@disabled)][1]"));
    private selectSave = element(by.xpath("//button[contains(text(),'Guardar')]"));

    async navigiteToEditPage(){
        await this.navigateToEdit.click();
    }
    async sendKeyInputSearchId(){
        await this.inputSearchIdClient.sendKeys(1);
    }

    async clickUpgradeButton(){
        await this.upgradeButton.click();
    }

    async clickUpdateButton(){
        await this.updateButton.click();
    }

    async clickSelectSeat(){
        await this.selectSeat.click();
    }

    async clickSelectOption(){
        await this.selectOption.click();
    }
    
    async clickSelectSave(){
        await this.selectSave.click();
    }
    


}   