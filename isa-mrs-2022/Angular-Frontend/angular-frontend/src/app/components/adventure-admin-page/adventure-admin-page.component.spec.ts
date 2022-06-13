import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureAdminPageComponent } from './adventure-admin-page.component';

describe('AdventureAdminPageComponent', () => {
  let component: AdventureAdminPageComponent;
  let fixture: ComponentFixture<AdventureAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureAdminPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
