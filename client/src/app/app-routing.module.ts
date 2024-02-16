import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    // {path: 'org',
    // component: OrgComponent, // this is the component with the <router-outlet> in the template
    // children: [
    //   {
    //     path: ':id', // child route path
    //     component: DetailOrgAComponent, // child route component that the router renders
    //     children: [
    //       {path: 'edit',
    //       component : EditOrgComponent,}

    //     ]
    //   },
    //   {
    //     path: 'create',
    //     component: CreateOrgBComponent, // another child route component that the router renders
    //   },
    // ]},
    // { path: 'profile',
    // component: ProfileComponent,
    // },
    // { path: 'bonnesPratiques',
    // component: BonnesPratiquesComponent,
    // children : [
    //   { path: ':id',
    //     component: DetailBonnesPratiquesComponent,
    //   }
    // ]
    // },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
