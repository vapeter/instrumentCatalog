import {Component, OnInit} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {TranslateService} from '@ngx-translate/core';
import {DomSanitizer, Title} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';
import {environment} from '../environments/environment';

export let selectedLanguage;

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    constructor(
        private keycloakService: KeycloakService,
        private translateService: TranslateService,
        private title: Title,
        private matIconRegistry: MatIconRegistry,
        private domSanitizer: DomSanitizer,
    ) {}

    supportLanguages = ['en', 'hu'];
    isLoggedIn = false;

    hunLogoSource = this.matIconRegistry.addSvgIcon(
        'logo_hu',
        this.domSanitizer
            .bypassSecurityTrustResourceUrl('assets/img/icons/instrument_catalog_hu.svg'));

    enLogoSource = this.matIconRegistry.addSvgIcon(
        'logo_en',
        this.domSanitizer
            .bypassSecurityTrustResourceUrl('assets/img/icons/instrument_catalog_en.svg'));

    ngOnInit(): void {
        this.setLanguage();
        this.setTitle();
        this.isAuthenticated();
    }

    logoSrc(): boolean {
        if (selectedLanguage === 'en') {
            return true;
        }
    }

    async isAuthenticated() {
        if (await this.keycloakService.isLoggedIn()) {
            await this.keycloakService.updateToken();
            this.isLoggedIn = true;
        }
    }

    setLanguage(): void {
        this.translateService.addLangs(this.supportLanguages);

        if (this.supportLanguages.includes(navigator.language)) {
            this.translateService.setDefaultLang(navigator.language);
            selectedLanguage = navigator.language;
            console.log('Selected Language => ', selectedLanguage);
        } else {
            this.translateService.setDefaultLang('en');
            selectedLanguage = 'en';
            console.log('Selected Language => ', selectedLanguage);
        }
    }

    async setTitle() {
        this.title.setTitle( await this.translateService.get('home.title').toPromise());
    }

    async useLang(lang: string) {
        selectedLanguage = lang;
        console.log('selected language ==> ', selectedLanguage);
        this.translateService.setDefaultLang(lang);
        this.setTitle();
    }

    logout(): void {
        this.keycloakService.logout(environment.redirectUrl);
        this.keycloakService.clearToken();
    }

    userLogin() {
        this.keycloakService.login();
    }
}
