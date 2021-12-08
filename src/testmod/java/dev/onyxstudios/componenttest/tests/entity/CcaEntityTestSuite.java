/*
 * Cardinal-Components-API
 * Copyright (C) 2019-2021 OnyxStudios
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package dev.onyxstudios.componenttest.tests.entity;

import dev.onyxstudios.componenttest.content.vita.Vita;
import io.github.ladysnake.elmendorf.GameTestUtil;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class CcaEntityTestSuite implements FabricGameTest {
    @GameTest(structureName = EMPTY_STRUCTURE)
    public void bucketableWorks(TestContext ctx) {
        var player = GameTestUtil.spawnPlayer(ctx, 1, 1, 1);
        player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.WATER_BUCKET));
        BlockPos pos = new BlockPos(2, 2, 2);
        var axolotl = ctx.spawnMob(EntityType.AXOLOTL, pos);
        Vita.get(axolotl).setVitality(3);
        Bucketable.tryBucket(player, Hand.MAIN_HAND, axolotl);
        ((EntityBucketItem) Items.AXOLOTL_BUCKET).onEmptied(player, ctx.getWorld(), player.getStackInHand(Hand.MAIN_HAND), ctx.getAbsolutePos(pos));
        ctx.expectEntityWithDataEnd(pos, EntityType.AXOLOTL, a -> Vita.get(a).getVitality(), 3);
    }
}