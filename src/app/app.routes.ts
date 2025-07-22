import { Routes } from '@angular/router';
import {Overview} from './pages/overview/overview';
import {Lampadaire} from './pages/lampadaire/lampadaire';
import {Consommation} from './pages/consommation/consommation';
import {Maintenance} from './pages/maintenance/maintenance';
import {DasbordMinee} from './pages/dasbord-minee/dasbord-minee';
import {Rapport} from './pages/rapport/rapport';
import {PredictionAi} from './pages/prediction-ai/prediction-ai';
import {Notification} from './pages/notification/notification';
import {Login} from './pages/login/login';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'dashboard-minee', component: DasbordMinee},
  // {path: '**', redirectTo: 'login'},
//  {path: 'overview', component: Overview},
  {path: 'lampadaire', component: Lampadaire},
  {path: 'consommation', component: Consommation},
  {path: 'maintenance', component: Maintenance},
  {path: 'rapport', component: Rapport},
  {path: 'prediction-ia', component:PredictionAi},
  {path: 'notification', component: Notification},
  {path: 'login', component: Login},
];
