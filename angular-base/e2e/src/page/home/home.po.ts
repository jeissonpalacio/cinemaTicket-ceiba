import { by, element } from 'protractor';

export class HomePage{
    private titleHomePage = element(by.xpath("/html/body/app-root/app-home/h1"))

    
    getPageHomeTitle(){
        expect(this.titleHomePage.getText()).toEqual("Cinema Ceiba");
    }
}