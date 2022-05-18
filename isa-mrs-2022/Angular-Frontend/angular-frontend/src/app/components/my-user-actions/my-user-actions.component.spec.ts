import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyUserActionsComponent } from './my-user-actions.component';

describe('MyUserActionsComponent', () => {
  let component: MyUserActionsComponent;
  let fixture: ComponentFixture<MyUserActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyUserActionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyUserActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
