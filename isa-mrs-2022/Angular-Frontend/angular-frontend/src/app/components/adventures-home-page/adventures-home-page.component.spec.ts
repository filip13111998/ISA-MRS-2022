import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventuresHomePageComponent } from './adventures-home-page.component';

describe('AdventuresHomePageComponent', () => {
  let component: AdventuresHomePageComponent;
  let fixture: ComponentFixture<AdventuresHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventuresHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventuresHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
