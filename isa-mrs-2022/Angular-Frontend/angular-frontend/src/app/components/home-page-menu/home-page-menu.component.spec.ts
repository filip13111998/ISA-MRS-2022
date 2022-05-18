import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageMenuComponent } from './home-page-menu.component';

describe('HomePageMenuComponent', () => {
  let component: HomePageMenuComponent;
  let fixture: ComponentFixture<HomePageMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageMenuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePageMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
