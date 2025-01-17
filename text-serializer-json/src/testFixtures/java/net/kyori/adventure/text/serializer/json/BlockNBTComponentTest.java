/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2023 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text.serializer.json;

import net.kyori.adventure.text.BlockNBTComponent;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;

final class BlockNBTComponentTest extends SerializerTest {
  @Test
  void testLocal() {
    this.testObject(
      Component.blockNBT().nbtPath("abc").localPos(1.23d, 2.0d, 3.89d).build(),
      json -> {
        json.addProperty(JSONComponentConstants.NBT, "abc");
        json.addProperty(JSONComponentConstants.NBT_INTERPRET, false);
        json.addProperty(JSONComponentConstants.NBT_BLOCK, "^1.23 ^2.0 ^3.89");
      }
    );
  }

  @Test
  void testAbsoluteWorld() {
    this.testObject(
      Component.blockNBT().nbtPath("xyz").absoluteWorldPos(4, 5, 6).interpret(true).build(),
      json -> {
        json.addProperty(JSONComponentConstants.NBT, "xyz");
        json.addProperty(JSONComponentConstants.NBT_INTERPRET, true);
        json.addProperty(JSONComponentConstants.NBT_BLOCK, "4 5 6");
      }
    );
  }

  @Test
  void testRelativeWorld() {
    this.testObject(
      Component.blockNBT().nbtPath("eeee").relativeWorldPos(7, 83, 900).build(),
      json -> {
        json.addProperty(JSONComponentConstants.NBT, "eeee");
        json.addProperty(JSONComponentConstants.NBT_INTERPRET, false);
        json.addProperty(JSONComponentConstants.NBT_BLOCK, "~7 ~83 ~900");
      }
    );
  }

  @Test
  void testMixedAbsoluteAndRelative() {
    this.testObject(
      Component.blockNBT().nbtPath("qwert").worldPos(
        BlockNBTComponent.WorldPos.Coordinate.absolute(12),
        BlockNBTComponent.WorldPos.Coordinate.relative(3),
        BlockNBTComponent.WorldPos.Coordinate.absolute(1200)
      ).build(),
      json -> {
        json.addProperty(JSONComponentConstants.NBT, "qwert");
        json.addProperty(JSONComponentConstants.NBT_INTERPRET, false);
        json.addProperty(JSONComponentConstants.NBT_BLOCK, "12 ~3 1200");
      }
    );
  }
}
