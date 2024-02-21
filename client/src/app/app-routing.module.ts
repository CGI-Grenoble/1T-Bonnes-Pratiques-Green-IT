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

const routes: Routes = [
    {path: 'org',
    component: OrgComponent, // this is the component with the <router-outlet> in the template
    /* children: [
      {
        // path: '', // child route path
        // component: OrgAccueilComponent, // Replace OrgDetailComponent with OrgComponent
        // children: [{
          path: 'detail', // child route path
          component: OrgDetailComponent, // Replace OrgDetailComponent with OrgComponent
          children: [
            // {path: 'edit',
            // component : EditOrgComponent,}
          ] */
  
        // },]

    // },
    //]
    //   {
    //     path: 'create',
    //     component: CreateOrgBComponent, // another child route component that the router renders
    //   },
    // ]},
    // { path: 'profile',
    // component: ProfileComponent,
    },
    {
      path: 'org/detail', // child route path
      component: OrgComponent, // Replace OrgDetailComponent with OrgComponent
      children: [
        // {path: 'edit',
        // component : EditOrgComponent,}
      ]
    },
    { path: 'org/create',
      component: OrgComponent
    },
    { path: '',
    component: BonnesPratiquesComponent,
    children : [
      // { path: ':id',
      //   component: DetailBonnesPratiquesComponent,
      // }
    ]
    },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
