import { by, element } from 'protractor';

export class NavbarPage {
    linkHome = element(by.xpath('/html/body/app-root/app-navbar/nav/a[1]'));
    linkMovie = element(by.xpath('/html/body/app-root/app-navbar/nav/a[2]'));

    async clickBottonHomePage() {
        await this.linkHome.click();
    }
    
    async clickBottonMoviePage(){
        await this.linkMovie.click();
    }
}
