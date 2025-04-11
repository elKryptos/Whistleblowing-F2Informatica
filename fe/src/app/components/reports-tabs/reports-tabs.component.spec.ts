import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportsTabsComponent } from './reports-tabs.component';

describe('ReportsTabsComponent', () => {
  let component: ReportsTabsComponent;
  let fixture: ComponentFixture<ReportsTabsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReportsTabsComponent]
    });
    fixture = TestBed.createComponent(ReportsTabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
