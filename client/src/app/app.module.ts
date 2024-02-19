import { NgModule } from '@angular/core';
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
<<<<<<< HEAD
import { CardModule } from 'primeng/card';
import { EditOrgaComponent } from './edit-orga/edit-orga.component';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ToastModule } from 'primeng/toast';
import { ToastComponent } from './toast/toast.component';
import { RadioComponent } from './radio/radio.component';
=======
import { OrgComponent } from './org/org.component';
import { CardModule } from 'primeng/card';
import { OrgDetailComponent } from './org-detail/org-detail.component';
import { BonnesPratiquesComponent } from './bonnes-pratiques/bonnes-pratiques.component';
import { BonnesPratiquesBoutonComponent } from './bonnes-pratiques-bouton/bonnes-pratiques-bouton.component';
import { BonnesPratiquesAccueilComponent } from './bonnes-pratiques-accueil/bonnes-pratiques-accueil.component';
import { OrgBoutonComponent } from './org-bouton/org-bouton.component';
import { OrgAccueilComponent } from './org-accueil/org-accueil.component';
>>>>>>> origin/Nav-bar
/* import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonToggleModule } from '@angular/material/button-toggle'; 
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarHarness} from '@angular/material/toolbar/testing'; 
 */

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
<<<<<<< HEAD
    EditOrgaComponent,
    ToastComponent,
    RadioComponent,
=======
    OrgComponent,
    OrgDetailComponent,
    BonnesPratiquesComponent,
    BonnesPratiquesBoutonComponent,
    BonnesPratiquesAccueilComponent,
    OrgBoutonComponent,
    OrgAccueilComponent,
>>>>>>> origin/Nav-bar
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
    MenubarModule,
<<<<<<< HEAD
    ToastModule,
    FormsModule,
    CardModule
=======
    CardModule,

>>>>>>> origin/Nav-bar
    /* MatSlideToggleModule,
    MatButtonToggleModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarHarness */
  ],
  providers: [],
  bootstrap: [AppComponent]
})


export class AppModule { }
