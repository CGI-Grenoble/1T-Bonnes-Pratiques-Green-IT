
import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { MenubarModule } from 'primeng/menubar';
import { ToastModule } from 'primeng/toast';
import { RadioComponent } from './radio/radio.component';
import { OrgComponent } from './org/org.component';
import { CardModule } from 'primeng/card';
import { OrgDetailComponent } from './org-detail/org-detail.component';
import { BonnesPratiquesComponent } from './bonnes-pratiques/bonnes-pratiques.component';
import { BonnesPratiquesBoutonComponent } from './bonnes-pratiques-bouton/bonnes-pratiques-bouton.component';
import { BonnesPratiquesAccueilComponent } from './bonnes-pratiques-accueil/bonnes-pratiques-accueil.component';
import { OrgBoutonComponent } from './org-bouton/org-bouton.component';
import { OrgAccueilComponent } from './org-accueil/org-accueil.component';
import { CommonModule } from '@angular/common';
import { ToastComponent } from './toast/toast.component';
import { DataViewModule } from 'primeng/dataview';
import {HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient} from "@angular/common/http";
import {KeycloakAngularModule, KeycloakBearerInterceptor, KeycloakService} from "keycloak-angular";
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { environment } from '../environments/environment';
import { OrgEditComponent } from './org-edit/org-edit.component';
import { MembersComponent } from './members/members.component';
import { OrgCreateComponent } from './org-create/org-create.component';
import { ReglesComponent } from './regles/regles.component';
import { FieldsetModule } from 'primeng/fieldset';
import { ScrollPanelModule } from 'primeng/scrollpanel';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: environment.keycloak.url,
        realm: environment.keycloak.realm,
        clientId: environment.keycloak.clientId,
      },
      enableBearerInterceptor: true,
      loadUserProfileAtStartUp: true,
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: true,
      },
      bearerPrefix: 'Bearer', // prefix "bearer <TOKEN> on each request
      bearerExcludedUrls: [],
      shouldAddToken: (request) => {
        return true
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    OrgComponent,
    OrgDetailComponent,
    BonnesPratiquesComponent,
    BonnesPratiquesBoutonComponent,
    BonnesPratiquesAccueilComponent,
    OrgBoutonComponent,
    OrgAccueilComponent,
    RadioComponent,
    ToastComponent,
    AdminComponent,
    UserComponent,
    AccessDeniedComponent,
    OrgEditComponent,
    MembersComponent,
    OrgCreateComponent,
    ReglesComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToolbarModule,
    SplitButtonModule,
    AvatarModule,
    AvatarGroupModule,
    InputGroupModule,
    InputGroupAddonModule,
    InputTextModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    MenubarModule,
    ToastModule,
    FormsModule,
    CardModule,
    CommonModule,
    ReactiveFormsModule,
    KeycloakAngularModule,
    DataViewModule,
    FieldsetModule,
    ScrollPanelModule
  ],
  bootstrap: [AppComponent],
  providers: [
    provideHttpClient(),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakBearerInterceptor,
      multi: true
    }
  ],
})
export class AppModule {}
