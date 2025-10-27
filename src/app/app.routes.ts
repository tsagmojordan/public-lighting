import { Routes } from '@angular/router';
import {Overview} from './pages/overview/overview';
import {Consommation} from './pages/consommation/consommation';
import {Maintenance} from './pages/maintenance/maintenance';
import {DasbordMinee} from './pages/dasbord-minee/dasbord-minee';
import {Rapports} from './pages/rapport/rapport';
import {PredictionAi} from './pages/prediction-ai/prediction-ai';
import { Notifications} from './pages/notification/notification';
import {Login} from './pages/login/login';
import {LampadaireManagementComponent} from './pages/lampadaire/lampadaire';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'dashboard-minee', component: DasbordMinee},
  // {path: '**', redirectTo: 'login'},
//  {path: 'overview', component: Overview},
  {path: 'lampadaire', component: LampadaireManagementComponent},
  {path: 'consommation', component: Consommation},
  {path: 'maintenance', component: Maintenance},
  {path: 'rapport', component: Rapports},
  {path: 'prediction-ia', component:PredictionAi},
  {path: 'notification', component: Notifications},
  {path: 'login', component: Login},
];
