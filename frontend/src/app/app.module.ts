import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ButtonComponent} from "./button/button.component";
import {ScenarioService} from "./scenario/scenario.service";
import {HttpClientModule} from "@angular/common/http";
import {RowAdderStringRowComponent} from "./row-adder/string-row/row-adder-string-row.component";
import {RowAdderStepRowComponent} from "./row-adder/step-row/row-adder-step-row.component";

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    RowAdderStringRowComponent,
    RowAdderStepRowComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ScenarioService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
