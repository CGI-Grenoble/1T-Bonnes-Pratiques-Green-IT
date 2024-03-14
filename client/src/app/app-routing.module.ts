import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrgComponent } from './org/org.component';
import { OrgAccueilComponent } from './org-accueil/org-accueil.component';
import { BonnesPratiquesComponent } from './bonnes-pratiques/bonnes-pratiques.component';
import { OrgDetailComponent } from './org-detail/org-detail.component'; // Import the missing OrgDetailComponent
import {AdminComponent} from "./admin/admin.component";
import {AuthGuard} from "./auth.guard";
import {UserComponent} from "./user/user.component";
import {AccessDeniedComponent} from "./access-denied/access-denied.component";
import { ReglesComponent } from './regles/regles.component';
import { SalonComponent } from './salon/salon.component';
import { OrgEditComponent } from './org-edit/org-edit.component';
import { JoinComponent } from './join/join.component';

const routes: Routes = [
    {path: 'org',
    component: OrgComponent, // this is the component with the <router-outlet> in the template
    data: { roles: ['user'] },
    children: [
      {
        // path: '', // child route path
        // component: OrgAccueilComponent, // Replace OrgDetailComponent with OrgComponent
        // children: [{
          path: 'detail/:id', // child route path
          component: OrgDetailComponent, // Replace OrgDetailComponent with OrgComponent

        },
      {
        path: '**',
        component: OrgDetailComponent
      },
      {
        path: 'edit/:id',
        component: OrgEditComponent
      }
    ]

    //]
    //   {
    //     path: 'create',
    //     component: CreateOrgBComponent, // another child route component that the router renders
    //   },
    // ]},
    // { path: 'profile',
    // component: ProfileComponent,
    },
    /* {
      path: 'org/detail', // child route path
      component: OrgComponent, // Replace OrgDetailComponent with OrgComponent
      children: [
        // {path: 'edit',
        // componen<p-card [header]="orga.name" [style]="{ width: '305px'}">
      <ng-template pTemplate="footer">
        <p-button icon="pi pi-add" [link]="true" [style]="{'color':'#FF978A'}"></p-button>
      </ng-template>
    </p-card>t : EditOrgComponent,}
      ],
      data: { roles: ['user'] },
    }, */
    {
      path: 'a-appliquer-en-priorite', // child route path
      component: BonnesPratiquesComponent, // Replace OrgDetailComponent with OrgComponent
      children: [
        // {path: 'edit',
        // component : EditOrgComponent,}
      ],
      data: { roles: ['user'] },
    },
    { path: 'org/create',
      component: OrgComponent
    },
    { path: 'org/edit',
      component: OrgComponent
    },
    {
      path: 'org/find',
      component: OrgComponent
    },
    { path: 'join',
      component: JoinComponent,
      children: [

      ]
    },
    {
      path: 'join/game/:id',
      component: SalonComponent
    },
    { path: '',
    component: BonnesPratiquesComponent,
    children : [
      // { path: ':id',
      //   component: DetailBonnesPratiquesComponent,
      // }
    ],
    data: { roles: ['user'] }
    },
    {
      path: 'regles',
      component: ReglesComponent
    }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
