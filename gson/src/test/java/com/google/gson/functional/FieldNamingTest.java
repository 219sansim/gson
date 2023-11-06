/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.functional;

import static com.google.gson.FieldNamingPolicy.IDENTITY;
import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_DASHES;
import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;
import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES;
import static com.google.gson.FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES;
import static com.google.common.truth.Truth.assertThat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.junit.Test;

public final class FieldNamingTest {
  @Test
  public void testIdentity() {
    Gson gson = getGsonWithNamingPolicy(IDENTITY);
    assertThat(gson.toJson(new TestNames()).replace('\"', '\''))
        .isEqualTo("{'UPPER_WORDS':6,'UpperCamel':2,'_9':9,'_UpperCamelLeadingUnderscore':4,'_lowerCamelLeadingUnderscore':3,'annotatedName':7,'lowerCamel':1,'lowerId':8,'lower_words':5}");
  }

  @Test
  public void testUpperCamelCase() {
    Gson gson = getGsonWithNamingPolicy(UPPER_CAMEL_CASE);
    assertThat(gson.toJson(new TestNames()).replace('\"', '\''))
        .isEqualTo("{'UPPER_WORDS':6,'UpperCamel':2,'_9':9,'_UpperCamelLeadingUnderscore':4,'_LowerCamelLeadingUnderscore':3,'annotatedName':7,'LowerCamel':1,'LowerId':8,'Lower_words':5}");
  }

  @Test
  public void testUpperCamelCaseWithSpaces() {
    Gson gson = getGsonWithNamingPolicy(UPPER_CAMEL_CASE_WITH_SPACES);
    assertThat(gson.toJson(new TestNames()).replace('\"', '\''))
        .isEqualTo("{'U P P E R_ W O R D S':6,'Upper Camel':2,'_9':9,'_ Upper Camel Leading Underscore':4,'_Lower Camel Leading Underscore':3,'annotatedName':7,'Lower Camel':1,'Lower Id':8,'Lower_words':5}");
  }

  @Test
  public void testUpperCaseWithUnderscores() {
    Gson gson = getGsonWithNamingPolicy(UPPER_CASE_WITH_UNDERSCORES);
    assertThat(gson.toJson(new TestNames()).replace('\"', '\''))
        .isEqualTo("{'U_P_P_E_R__W_O_R_D_S':6,'UPPER_CAMEL':2,'_9':9,'__UPPER_CAMEL_LEADING_UNDERSCORE':4,'_LOWER_CAMEL_LEADING_UNDERSCORE':3,'annotatedName':7,'LOWER_CAMEL':1,'LOWER_ID':8,'LOWER_WORDS':5}");
  }

  @Test
  public void testLowerCaseWithUnderscores() {
    Gson gson = getGsonWithNamingPolicy(LOWER_CASE_WITH_UNDERSCORES);
    assertThat(gson.toJson(new TestNames()).replace('\"', '\''))
        .isEqualTo("{'u_p_p_e_r__w_o_r_d_s':6,'upper_camel':2,'_9':9,'__upper_camel_leading_underscore':4,'_lower_camel_leading_underscore':3,'annotatedName':7,'lower_camel':1,'lower_id':8,'lower_words':5}");
  }
  
  @Test
  public void testLowerCaseWithDashes() {
    Gson gson = getGsonWithNamingPolicy(LOWER_CASE_WITH_DASHES);
    assertThat(gson.toJson(new TestNames()).replace('\"', '\''))
        .isEqualTo("{'u-p-p-e-r_-w-o-r-d-s':6,'upper-camel':2,'_9':9,'_-upper-camel-leading-underscore':4,'_lower-camel-leading-underscore':3,'annotatedName':7,'lower-camel':1,'lower-id':8,'lower_words':5}");
  }

  private Gson getGsonWithNamingPolicy(FieldNamingPolicy fieldNamingPolicy){
    return new GsonBuilder()
      .setFieldNamingPolicy(fieldNamingPolicy)
        .create();
  }

  @SuppressWarnings("unused") // fields are used reflectively
  private static class TestNames {
    int lowerCamel = 1;
    int UpperCamel = 2;
    int _lowerCamelLeadingUnderscore = 3;
    int _UpperCamelLeadingUnderscore = 4;
    int lower_words = 5;
    int UPPER_WORDS = 6;
    @SerializedName("annotatedName") int annotated = 7;
    int lowerId = 8;
    int _9 = 9;
  }
}
