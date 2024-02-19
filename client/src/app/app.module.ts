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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { MenubarModule } from 'primeng/menubar';
import {provideHttpClient} from "@angular/common/http";
import {AuthConfig, OAuthService, provideOAuthClient} from "angular-oauth2-oidc";
/* import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarHarness} from '@angular/material/toolbar/testing';
 */

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8080/realms/1t-bonnes-pratiques',
  tokenEndpoint: 'http://localhost:8080/realms/1t-bonnes-pratiques/protocol/openid-connect/token',
  redirectUri: window.location.origin,
  clientId: 'green-it-app',
  responseType: 'code',
  scope: 'openid profile'
}

function initializeOAuth(oauthService: OAuthService): Promise<void> {
  return new Promise((resolve) => {
    oauthService.configure(authCodeFlowConfig)
    oauthService.setupAutomaticSilentRefresh()
    oauthService.loadDiscoveryDocumentAndLogin()
      .then(() => resolve())
  })
}

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
  ],
  imports: [
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
    MenubarModule
    /* MatSlideToggleModule,
    MatButtonToggleModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarHarness */
  ],
  providers: [
    provideHttpClient(),
    provideOAuthClient(),
    {
      provide: APP_INITIALIZER,
      useFactory: (oauthService: OAuthService) => {
        return () => {
          initializeOAuth(oauthService)
        }
      },
      multi: true,
      deps: [
        OAuthService
      ]
    }
  ],
  bootstrap: [AppComponent]
})


export class AppModule { }
