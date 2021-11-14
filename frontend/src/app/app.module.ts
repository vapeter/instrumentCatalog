import {BrowserModule, Title} from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentsComponent } from './components/students/students.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { InstrumentsComponent } from './components/instruments/instruments.component';
import { YesNoPipe } from './yes-no.pipe';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import { InstrumentComponent } from './components/instruments/instrument/instrument.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import {RentComponent} from './components/instruments/rent/rent.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatSelectFilterModule} from 'mat-select-filter';
import {NgSelectModule} from '@ng-select/ng-select';
import {MtxSelectModule} from '@ng-matero/extensions';
import {NgxMatSelectSearchModule} from 'ngx-mat-select-search';
import {MatMomentDateModule} from '@angular/material-moment-adapter';
import {DatePipe} from '@angular/common';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import {HttpErrorInterceptor} from './intercreptor/http-error.interceptor';
import { TeachersComponent } from './components/teachers/teachers.component';
import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';
import { environment } from 'src/environments/environment';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { WelcomeComponent } from './components/welcome/welcome.component';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: environment.keycloakUrl,
        realm: 'instrumentCatalog',
        clientId: 'catalog',
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html',
      },
    });
}

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    InstrumentsComponent,
    YesNoPipe,
    InstrumentComponent,
    RentComponent,
    FileUploadComponent,
    TeachersComponent,
    WelcomeComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatSelectModule,
        MatButtonModule,
        FormsModule,
        MatDialogModule,
        ReactiveFormsModule,
        MatRadioModule,
        MatSnackBarModule,
        MatTooltipModule,
        MatProgressSpinnerModule,
        MatProgressBarModule,
        MatMenuModule,
        MatToolbarModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatAutocompleteModule,
        MatSelectFilterModule,
        NgSelectModule,
        MtxSelectModule,
        NgxMatSelectSearchModule,
        MatMomentDateModule,
        MatCheckboxModule,
        KeycloakAngularModule,
        MatSidenavModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: (http: HttpClient) => new TranslateHttpLoader(http, './assets/i18n/', '.json'),
                deps: [HttpClient]
            }
        }),
        MatListModule
    ],
  providers: [
    DatePipe,
    { provide: APP_INITIALIZER, useFactory: initializeKeycloak, multi: true, deps: [KeycloakService], },
    {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true},
    Title
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
