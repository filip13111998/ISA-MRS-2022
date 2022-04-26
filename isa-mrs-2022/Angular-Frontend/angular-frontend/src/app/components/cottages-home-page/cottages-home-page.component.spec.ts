import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottagesHomePageComponent } from './cottages-home-page.component';

describe('CottagesHomePageComponent', () => {
  let component: CottagesHomePageComponent;
  let fixture: ComponentFixture<CottagesHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottagesHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottagesHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
