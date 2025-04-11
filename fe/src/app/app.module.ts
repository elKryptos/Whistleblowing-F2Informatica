import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NgIconsModule } from '@ng-icons/core';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { RecipientDashboardComponent } from './pages/recipient-dashboard/recipient-dashboard.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { TokenInterceptor } from './interceptors/token-interceptor';
import {
  lucideCheck,
  lucideChevronDown,
  lucideFileSpreadsheet,
  lucideFiles,
  lucideHome,
  lucideLineChart,
  lucideLogOut,
  lucideMessageCircle,
  lucideMoveRight,
  lucidePlus,
  lucideSettings,
  lucideUser,
  lucideUserCog,
  lucideUsers,
  lucideX,
} from '@ng-icons/lucide';
import { ErrorAlertComponent } from './components/error-alert/error-alert.component';
import { UsersManagementComponent } from './components/users-management/users-management.component';
import { ReportsTableComponent } from './components/reports-table/reports-table.component';
import { AddUserModalComponent } from './modals/add-user-modal/add-user-modal.component';
import { DeleteUserModalComponent } from './modals/delete-user-modal/delete-user-modal.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardHeaderComponent } from './components/dashboard-header/dashboard-header.component';
import { ReportsTabsComponent } from './components/reports-tabs/reports-tabs.component';
import { ReportAssignmentComponent } from './modals/report-assignment/report-assignment.component';
import { ReportViewModalComponent } from './modals/report-view-modal/report-view-modal.component';
import { UserReportViewComponent } from './pages/user-report-view/user-report-view.component';
import { AnswersSummaryComponent } from './components/answers-summary/answers-summary.component';
import { CommentsComponent } from './components/comments/comments.component';
import { ReportComponent } from './pages/report/report.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OverviewComponent } from './components/overview/overview.component';
import { StatusReportsComponent } from './components/status-reports/status-reports.component';
import { ChartPreviewComponent } from './components/chart-preview/chart-preview.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RecipientDashboardComponent,
    AdminDashboardComponent,
    NavbarComponent,
    SignInComponent,
    ResetPasswordComponent,
    ErrorAlertComponent,
    UsersManagementComponent,
    ReportsTableComponent,
    AddUserModalComponent,
    DeleteUserModalComponent,
    SearchBarComponent,
    SidebarComponent,
    DashboardHeaderComponent,
    ReportsTabsComponent,
    ReportAssignmentComponent,
    ReportViewModalComponent,
    UserReportViewComponent,
    AnswersSummaryComponent,
    CommentsComponent,
    ReportComponent,
    OverviewComponent,
    StatusReportsComponent,
    ChartPreviewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgIconsModule.withIcons({
      lucideMoveRight,
      lucideX,
      lucidePlus,
      lucideUser,
      lucideUserCog,
      lucideHome,
      lucideFileSpreadsheet,
      lucideUsers,
      lucideSettings,
      lucideLogOut,
      lucideChevronDown,
      lucideMessageCircle,
      lucideFiles,
      lucideCheck,
      lucideLineChart,
    }),
    FormsModule,
    BrowserAnimationsModule,
    MatSelectModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
