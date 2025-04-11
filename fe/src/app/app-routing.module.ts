import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { RecipientDashboardComponent } from './pages/recipient-dashboard/recipient-dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { UserReportViewComponent } from './pages/user-report-view/user-report-view.component';
import { adminGuard } from './guards/admin-guard';
import { recipientGuard } from './guards/recipient-guard';
import { ReportComponent } from './pages/report/report.component';

const routes: Routes = [
  {
    path: 'dashboard/admin',
    component: AdminDashboardComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'dashboard/recipient',
    component: RecipientDashboardComponent,
    canActivate: [recipientGuard],
  },
  { path: 'signIn', component: SignInComponent },
  {
    path: 'resetPassword',
    component: ResetPasswordComponent,
  },
  { path: 'resetPassword/:id', component: ResetPasswordComponent },
  { path: 'report/:code', component: UserReportViewComponent },
  { path: 'report', component: ReportComponent },
  { path: '**', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
